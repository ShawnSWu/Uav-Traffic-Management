package com.nutn.utm.controller;

import com.nutn.utm.exception.InvalidRequestException;
import com.nutn.utm.model.dto.form.LoginFormDto;
import com.nutn.utm.model.dto.response.LogInResponseDto;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginFormDto loginFormDto, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            throw new InvalidRequestException(bindingResult.getFieldErrors());
        String pilotAccount = loginFormDto.getPilotAccount().trim();
        String password = loginFormDto.getPassword().trim();

        LogInResponseDto logInResponseDto = accountService.logIn(pilotAccount, password);

        if(logInResponseDto.getToken().equals("")){
            return new ResponseEntity<>(logInResponseDto, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok().body(logInResponseDto);
    }


}
