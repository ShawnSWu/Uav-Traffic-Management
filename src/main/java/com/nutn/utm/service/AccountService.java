package com.nutn.utm.service;

import com.nutn.utm.model.dto.form.SignUpFormDto;
import com.nutn.utm.model.dto.response.LogInResponseDto;
import com.nutn.utm.model.dto.response.SignUpResponseDto;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.model.entity.Uav;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface AccountService {

    LogInResponseDto logIn(String account, String hashedPassword);

    SignUpResponseDto signUp(SignUpFormDto signUpFormDto);

    Pilot getPilotByAccountId(long accountId);

    Pilot getPilotByAccount(String account);

    boolean verifyPilotExist(String account);

    List<Uav> getAllUavOfPilot(long accountId);

}
