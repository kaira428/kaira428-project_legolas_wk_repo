package com.upskill.legolas.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table (name = "users_roles")
public class Role {
    
    public Role() {
    }

    public Role(@Size(max = 20) String role) {
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rowId;

    // @Column (name = "fk_user_id")
    // private Long fk_user_id;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Column (name = "role")
    @Size(max=20)
    private String role;

    // public Long getFk_user_id() {
    //     return fk_user_id;
    // }

    // public void setFk_user_id(Long fk_user_id) {
    //     this.fk_user_id = fk_user_id;
    // }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Role [role=" + role + "]";
    } 
}
