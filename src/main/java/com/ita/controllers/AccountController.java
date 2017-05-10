package com.ita.controllers;

import com.ita.entities.Account;
import com.ita.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by zelnaga on 07.02.17.
 */

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountsRepository repository;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> create(@RequestBody Account input) {

        return repository.findByLogin(input.getLogin())
                .map(a -> ResponseEntity.status(HttpStatus.CONFLICT).body("user.already.exist"))
                .orElseGet(() -> {
                    repository.saveAndFlush(new Account(input.getLogin(), input.getPassword(), input.getName(),
                                                        input.getDateOfBirth(), input.getCourse(), input.getSellPhone(), input.getSkype(), input.getGmail()));
                    return ResponseEntity.status(HttpStatus.OK).body("user.created");
                });

    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> delete(Principal principal) {

        return repository.findByLogin(principal.getName())
                .map(a -> {

                    repository.delete(a);
                    return ResponseEntity.status(HttpStatus.OK).body("user.deleted");
                })
                .orElseGet(() -> {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("user.!found");
                });

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> read(Principal principal) {
        Optional<Account> account = repository.findByLogin(principal.getName());
        return ResponseEntity.ok(account.get());
    }

    /*@RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseBody> update(Principal principal, @RequestBody Account input) {
        return
    }*/
}
