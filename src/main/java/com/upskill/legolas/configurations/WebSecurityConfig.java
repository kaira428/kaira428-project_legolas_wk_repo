
package com.upskill.legolas.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.upskill.legolas.services.CustomUserDetailService;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

		
	@Bean
	public UserDetailsService userDetailsService()
	{
		
		return new CustomUserDetailService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());
		
	//	auth.inMemoryAuthentication().withUser("geco@geco.asia")
	//	.password("gecoasia").roles("ADMIN");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/login","/resetPassword","/newAccountSignup","/css/**","/images/**").permitAll()
			// .anyRequest().authenticated()
		    .anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("email")
			.defaultSuccessUrl("/userlist")
		//	.failureUrl("/login-error")
			.permitAll()
		//	.and()
		//	.oauth2Login()
		//	.loginPage("/login")
		//	.defaultSuccessUrl("/")
		//	.permitAll()
		//	.failureUrl("/login-error")
			.and()
			.logout().permitAll()
		//	.logoutUrl("/login");
			.logoutSuccessUrl("/login");
	}
}
