package com.nutn.utm.controller;

import com.nutn.utm.exception.InvalidRequestException;
import com.nutn.utm.model.dto.form.LoginFormDto;
import com.nutn.utm.model.dto.form.SignUpFormDto;
import com.nutn.utm.model.dto.response.LogInResponseDto;
import com.nutn.utm.model.dto.response.SignUpResponseDto;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
        String pilotAccount = loginFormDto.getPilotAccount().trim();
        String password = loginFormDto.getPassword().trim();

        LogInResponseDto logInResponseDto = accountService.logIn(pilotAccount, password);

        if(logInResponseDto.getToken().equals("")){
            return new ResponseEntity<>(logInResponseDto, HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok().body(logInResponseDto);
    }


    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpFormDto signUpFormDto, BindingResult formFormatValidateResult) {
        if(formFormatValidateResult.hasErrors())
            throw new InvalidRequestException(formFormatValidateResult.getFieldErrors());
        SignUpResponseDto successSignUpResponseDto = accountService.signUp(signUpFormDto);
        return new ResponseEntity<>(successSignUpResponseDto, HttpStatus.OK);
    }

    @GetMapping("/drones")
    public List<Uav> getFlightPlanByDateAndId(Authentication authentication) {
        long accountId = Long.parseLong(authentication.getPrincipal().toString());
        return accountService.getAllUavOfPilot(accountId);
    }

}
