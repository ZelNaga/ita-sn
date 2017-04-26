package com.ita.controllers;

import com.ita.dto.PostDTO;
import com.ita.entities.Account;
import com.ita.entities.Post;
import com.ita.repositories.AccountsRepository;
import com.ita.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Post>> getLastFive(Principal principal,  Pageable pageable) {

        List<Post> posts = new LinkedList();

        return accountsRepository.findByLogin(principal.getName())
                .map(a -> ResponseEntity.status(HttpStatus.OK)
                        .body(postRepository.findAllByAccountIdOrderByIdDesc(a.getId(), pageable)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(posts));

    }
}
