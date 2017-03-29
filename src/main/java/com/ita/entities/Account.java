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

    protected Account() {

    }

    public Account(String login, String password, String name) {

        this.login = login;
        this.password = password;
        this.name = name;
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

    @Override
    public String toString() {

        return String.format("Account[id=%d, login='%s']", id, login);

    }
}
