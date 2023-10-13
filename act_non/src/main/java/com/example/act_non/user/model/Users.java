package com.example.act_non.user.model;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Users {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean flagOnline;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
    joinColumns = {@JoinColumn(name = "users_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> roles;

    public Users() {
    }

    public Users(Long id, String username, String password, Boolean flagOnline, Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.flagOnline = flagOnline;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public Boolean getFlagOnline() {
        return flagOnline;
    }

    public void setFlagOnline(Boolean flagOnline) {
        this.flagOnline = flagOnline;
    }
}
