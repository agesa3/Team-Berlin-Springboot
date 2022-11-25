package com.spaceyatech.berlin.controller;


import com.spaceyatech.berlin.requests.LoginRequest;
import com.spaceyatech.berlin.requests.SignUpRequest;
import com.spaceyatech.berlin.requests.TokenRefreshRequest;
import com.spaceyatech.berlin.response.JwtResponse;
import com.spaceyatech.berlin.response.MessageResponse;
import com.spaceyatech.berlin.response.TokenRefreshResponse;
import com.spaceyatech.berlin.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@NoArgsConstructor

@Slf4j
public class AuthController {


    @Autowired
    private UserService userService;




    @PostMapping("/signin")

    @Tag(name="UserSignIn")
    public ResponseEntity<JwtResponse> login( @Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse response = userService.signIn(loginRequest);

        //return response;

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);



    }

    @PostMapping("/signup")
    @Tag(name="UserSignUp")
    public ResponseEntity<MessageResponse> Register(@RequestBody @Valid SignUpRequest signUpRequest) {

        //return userService.signUp(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body( userService.signUp(signUpRequest));

    }

    @PostMapping("/refresh-token")
    @Tag(name="Renew Refresh Token")
    public ResponseEntity<TokenRefreshResponse> RefreshToken(@RequestBody @Valid TokenRefreshRequest tokenRefreshRequest) {

        log.info(" RefreshToken to generate new access token:-->{}",tokenRefreshRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body( userService.genarateRefreshToken(tokenRefreshRequest));

        //return userService.genarateRefreshToken(tokenRefreshRequest);


    }



}
