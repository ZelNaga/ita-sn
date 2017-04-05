package com.ita.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/logout")
public class LogOutController {

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> logOut(OAuth2Authentication auth) {

        OAuth2AccessToken accessToken = authorizationServerTokenServices.getAccessToken(auth);
        System.err.println(accessToken.getValue());
        boolean isLoggedOut = consumerTokenServices.revokeToken(accessToken.getValue());
        HttpStatus httpStatus = isLoggedOut ? HttpStatus.OK : HttpStatus.CONFLICT;
        String msg = isLoggedOut ? "logged.out" : "error";
        return ResponseEntity.status(httpStatus).body("user.!found");
    }
}
