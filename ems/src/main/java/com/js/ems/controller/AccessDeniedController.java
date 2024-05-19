package com.js.ems.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
public class AccessDeniedController {

    @GetMapping("/403")
    @Hidden
    public ResponseEntity<String> handleAccessDeniedError(Principal user) {
        if (user != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Hi " + user.getName() + ", you do not have permission to perform this operation!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You do not have permission to perform this operation!");
        }
    }
}
