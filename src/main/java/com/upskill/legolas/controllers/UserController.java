package com.upskill.legolas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.upskill.legolas.models.Password;
import com.upskill.legolas.models.Role;
import com.upskill.legolas.models.User;
import com.upskill.legolas.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

    @GetMapping("/")
	public String Init(Model model, HttpSession session) {
			
		System.out.println("Start up on endpoint: '/' Controller init");

		return "login";
	}

    @GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}

    @GetMapping("/user_signup")
    public String userSignUp(Model model){

        User user = new User();
        model.addAttribute("user", user);

        return "newAccountSignup";
    }

    @PostMapping("/process_register")
    public String processRegisterUser(@Valid @ModelAttribute("user")User user,
    BindingResult bindingResult,
    RedirectAttributes ra) {

        if (bindingResult.hasErrors()){
            return "newAccountSignup";
        }

        //check if user account already exists via email
        User tempUser = userRepository.findByEmail(user.getEmail());

        if (tempUser != null) {
            //user account already exists
            ra.addFlashAttribute("failedMessage", "User Account Already Exists!");
            return "redirect:/user_signup";
        }

        //Bcrypt user's password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);

        //create new user account in DB
        userRepository.saveAndFlush(user);

        //retrieve newly create user User ID
        tempUser = userRepository.findByEmail(user.getEmail());

        //create and assign 'Unassigned' role to new user
        Role tempRole = new Role("Unassigned");
        
        tempUser.add(tempRole);

        //set user_status to 'Unassigned'
        tempUser.setUser_status("New-Unassigned");

        //save new user with role=Unassigned
        userRepository.save(tempUser);

        ra.addFlashAttribute("message", "User account successfully created! Please login with new credential.");
        return "redirect:/login";
    }
    
    //Get list of users
    @GetMapping("/userlist")
	public String listUsers(Model model)
	{
		List<User> listUsers = userRepository.findAll();
		model.addAttribute("listusers", listUsers);
		
		for(int i = 0; i < listUsers.size(); i++) {
			System.out.println(listUsers.get(i).getRoles().get(0).getRole());			
		}	
		return "userlist";
	}

    //Update user details
    @PostMapping("/updateUserDetails")
    public String updateUserDetails(@ModelAttribute("user")User theUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "update_user_details";
        }

        return null;
    }

    //Update user password
    @PostMapping("/updateUserPassword")
    public String updateUserPassword(@ModelAttribute("user")User theUser,
    @ModelAttribute("thePassword")Password thePassword,
    BindingResult bindingResult,
    RedirectAttributes ra,
    Model model) {

        if (bindingResult.hasErrors()) {
            ra.addFlashAttribute("failedMessage", "Password Change Failed!");
            return "update_user_password";
        }

        //current password enter by user
        String currentPassword = thePassword.getCurrentPassword();

        //get the encrypted password from the User DB
        Long userId = theUser.getUser_id();

        User loginUser = userRepository.getById(userId);
        String currentEncryptedPassword = loginUser.getPassword();

        //check if entered password matches the one in DB
        boolean result = bcryptPasswordEncoder.matches(currentPassword, currentEncryptedPassword);

        if (result) {
//		Current entered password matched the one in DB.
//		Encrypt the new password and set it in the User object, loginUser, before saving it
			String encryptedPassword = bcryptPasswordEncoder.encode(thePassword.getNewPassword());
            
            loginUser.setPassword(encryptedPassword);

        // Save new password to User DB
            userRepository.save(loginUser);

            ra.addFlashAttribute("message", "Password successfully changed.");
        }
        else {
            ra.addFlashAttribute("failedMessage", "Password change FAILED.");
        }
        return "placeholder for redirect URL";
    }

    @GetMapping("/forgetpassword")
    public String forgetPassword (Model model) {
        return "resetPassword";
    }

    @RequestMapping("/resetPassword")
	public String resetPassword (
        @RequestParam(value = "registeredEmail", required = true) String registeredEmail,
        RedirectAttributes ra,
        Model model)
	{
        // System.out.printf("registered Email : %s\n", registeredEmail);

        //check against db if registered email is valid
        User user = userRepository.findByEmail(registeredEmail);

        model.addAttribute("user", user);

        if (user == null) {
            ra.addFlashAttribute("failedMessage", "This is not a valid registered email!");
            return "redirect:/forgetpassword";
        }

        //code to send email to user with reset link to be placed here.

		return "confirmPassword";
	}

    @RequestMapping("/resetToNewPassword")
    public String resetToNewPassword (
        @RequestParam(value = "newPassword", required = true)String newPassword,
        @RequestParam(value = "confirmPassword")String confirmPassword,
        @ModelAttribute("user")User theUser,
        RedirectAttributes ra) {

            //check if newPassword equals confirmPassword
            if (!(newPassword.equals(confirmPassword))) {
                ra.addFlashAttribute("failedMessage", "Passwords Do Not Match!");
                System.out.println("Passwords do not match");
                return "redirect:/forgetpassword";
            }

            //Bcrypt new password
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(newPassword);

            //get user object and update password into DB
            User user = userRepository.findById(theUser.getUser_id()).orElse(null);

            if (user == null) {
                ra.addFlashAttribute("failedMessage", "User does not exist!");
                return "redirect:/login";
            }

            //set new password for user and save to DB
            user.setPassword(encodedPassword);
            userRepository.save(user);

            ra.addFlashAttribute("message", "Password successfully reset.");
            return "redirect:/login";
        }
}

