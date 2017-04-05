package com.ita.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by asv on 01.04.17.
 */

@Entity
public class Post {

    @ManyToOne
    @JsonIgnore
    private Account account;

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    protected Post() {}

    public Post(Account account, String text) {

        this.account = account;
        this.text = text;
    }

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
