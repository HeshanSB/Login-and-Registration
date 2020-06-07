package com.example.demo.entity;

import javax.persistence.*;

/**
 * Created By Heshan
 * Created Date 6/6/2020
 */
@Entity
@Table(name = "user")
public class UserEntity {
    private String username;
    private String password;
    private String firstname;
    private String middlename;
    private String lastname;
    private String country;

    public UserEntity() {
    }

    public UserEntity(String username, String password, String firstname, String middlename, String lastname, String country) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.country = country;
    }

    @Id
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "middlename")
    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
