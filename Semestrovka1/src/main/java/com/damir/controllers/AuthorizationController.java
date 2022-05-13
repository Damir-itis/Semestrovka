package com.damir.controllers;

import com.damir.dto.SignUpDto;
import com.damir.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/")
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid SignUpDto signUpDto) {
        authorizationService.registration(signUpDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/confirm/{confirmCode}")
    public ResponseEntity<?> confirm(@PathVariable String confirmCode){
        authorizationService.confirm(confirmCode);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
