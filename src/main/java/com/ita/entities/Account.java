package com.ita.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by zelnaga on 07.02.17.
 */

@Entity
public class Account {


    @Id
    @GeneratedValue
    private Long id;

    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String name;

    private String dateOfBirth;

    private String course;

    private String sellPhone;

    private String skype;

    private String gmail;

    protected Account() {

    }

    public Account(String login, String password, String name, String dateOfBirth, String course, String sellPhone, String skype, String gmail) {

        this.login = login;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.course = course;
        this.sellPhone = sellPhone;
        this.skype = skype;
        this.gmail = gmail;
    }

    public Long getId() {

        return id;

    }

    public String getLogin() {

        return login;

    }

    public Account setLogin(String login) {

        this.login = login;
        return this;

    }

    public String getPassword() {

        return password;

    }

    public Account setPassword(String password) {

        this.password = password;
        return this;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSellPhone() {
        return sellPhone;
    }

    public void setSellPhone(String sellPhone) {
        this.sellPhone = sellPhone;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String toString() {

        return String.format("Account[id=%d, login='%s']", id, login);

    }
}
