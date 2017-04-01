package com.ita.controllers;

import com.ita.dto.PostDTO;
import com.ita.entities.Account;
import com.ita.entities.Post;
import com.ita.repositories.AccountsRepository;
import com.ita.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by asv on 01.04.17.
 */

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> create(Principal principal, @RequestBody PostDTO input) {

        return accountsRepository.findByLogin(principal.getName())
                .map(a -> {
                    postRepository.save(new Post(a, input.getText()));
                    return ResponseEntity.status(HttpStatus.OK).body("post.created");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).body("user.!found"));

    }
}
