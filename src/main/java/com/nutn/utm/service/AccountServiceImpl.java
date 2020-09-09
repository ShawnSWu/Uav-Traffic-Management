package com.nutn.utm.service;

import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.model.dto.response.LogInResponseDto;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.dto.response.message.AuthenticationMessage;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.repository.PilotRepository;
import com.nutn.utm.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    PilotRepository pilotRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    private boolean isLoginSuccess(Pilot pilot, String password){
        return passwordEncoder.matches(password ,pilot.getHashedPassword());
    }

    @Override
    public LogInResponseDto logIn(String account, String hashedPassword) {
        Pilot confirmedPilot = checkPilotIsExists(account);
        if(isLoginSuccess(confirmedPilot, hashedPassword)) {
            String token = jwtService.createToken(confirmedPilot);
            return new LogInResponseDto(token, AuthenticationMessage.JWT_AUTHORIZATION_OK);
        }
        return new LogInResponseDto("",AuthenticationMessage.ACCOUNT_OR_PASSWORD_NOT_MATCH);
    }

    private Pilot checkPilotIsExists(String account){
        Pilot pilot = pilotRepository.findByAccount(account);
        if (!Optional.ofNullable(pilot).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT);
        return pilot;
    }
}
