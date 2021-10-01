package com.upskill.legolas.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "users")
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;
    
    @Column(name = "email")
    @Size(min=5, max=50)
    @NotNull
    @Email(message = "Should be a valid email address; eg abc@abc.com")
    private String email;

    @Column (name = "password")
    @NotNull
    @Size(max=70)
    private String password;

    @Column (name = "first_name")
    @NotNull
    @Size(min=3, max=30)
    private String first_name;

    @Column (name = "last_name")
    @NotNull
    @Size(min=3, max=30)
    private String last_name;

    @Column (name = "mobile")
    @Size(min = 7, max =20)
    private String mobile;
    
    @Column (name = "country")
    @NotNull
    @Size(max=30)
    private String country;

    @Column (name = "user_status")
    @Size(max=20)
    private String user_status;

    @OneToMany(mappedBy = "user", cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<LearningTrackUser> learningTrackUsers;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<TopicUser> topicUsers;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private List<ModuleProgress> moduleProgresses;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<LearningTrackUser> getLearningTrackUsers() {
        return learningTrackUsers;
    }

    public void setLearningTrackUsers(List<LearningTrackUser> learningTrackUsers) {
        this.learningTrackUsers = learningTrackUsers;
    }

    public List<TopicUser> getTopicUsers() {
        return topicUsers;
    }

    public void setTopicUsers(List<TopicUser> topicUsers) {
        this.topicUsers = topicUsers;
    }

    public List<ModuleProgress> getModuleProgresses() {
        return moduleProgresses;
    }

    public void setModuleProgresses(List<ModuleProgress> moduleProgresses) {
        this.moduleProgresses = moduleProgresses;
    }

    //set up convenience method for roles
    public void add(Role tempRole) {

        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(tempRole);

        tempRole.setUser(this);
    }

    @Override
    public String toString() {
        return "User [country=" + country + ", email=" + email + ", first_name=" + first_name + ", last_name="
                + last_name + ", password=" + password + ", user_id=" + user_id + ", user_status=" + user_status + "]";
    }
    
}
