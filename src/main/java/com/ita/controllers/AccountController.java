package com.ita.controllers;

import com.ita.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by zelnaga on 07.02.17.
 */

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountsRepository repository;

    /*@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseBody> create(@RequestBody Account input) {

        return repository.findByLogin(input.getLogin())
                .map(a -> {

                    String message = String.format("user.exist - [%s]", a.getLogin());
                    ResponseBody body = new ResponseBody.RBBuilder(HttpStatus.CONFLICT, message).build();
                    return ResponseEntity.ok(body);

                }).orElseGet(() -> {

                    Account account = repository.saveAndFlush(new Account(input.getLogin(), input.getPassword()));
                    String message = String.format("user.created - [%s]", account.getLogin());
                    ResponseBody body = new ResponseBody.RBBuilder(HttpStatus.CREATED, message).build();
                    return ResponseEntity.ok(body);

                });

    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> read(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    /*@RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseBody> update(Principal principal, @RequestBody Account input) {
        return
    }*/
}
