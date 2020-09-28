package com.nutn.utm.service;

import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.exception.SignUpException;
import com.nutn.utm.model.dto.form.SignUpFormDto;
import com.nutn.utm.model.dto.response.LogInResponseDto;
import com.nutn.utm.model.dto.response.SignUpResponseDto;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.dto.response.message.AuthenticationMessage;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.repository.PilotRepository;
import com.nutn.utm.repository.UavRepository;
import com.nutn.utm.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.nutn.utm.model.dto.response.message.AuthenticationMessage.ACCOUNT_EXIST;
import static com.nutn.utm.model.dto.response.message.AuthenticationMessage.SIGN_UP_SUCCESS_MESSAGE;

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

    @Autowired
    UavRepository uavRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private boolean isLoginSuccess(Pilot pilot, String password) {
        return passwordEncoder.matches(password, pilot.getHashedPassword());
    }

    @Override
    public LogInResponseDto logIn(String account, String hashedPassword) {
        Pilot confirmedPilot = getPilotByAccount(account);
        if (isLoginSuccess(confirmedPilot, hashedPassword)) {
            String token = jwtService.createToken(confirmedPilot);
            return new LogInResponseDto(token, confirmedPilot, AuthenticationMessage.JWT_AUTHORIZATION_OK);
        }
        return new LogInResponseDto("", confirmedPilot, AuthenticationMessage.ACCOUNT_OR_PASSWORD_NOT_MATCH);
    }

    @Override
    public SignUpResponseDto signUp(SignUpFormDto signUpFormDto) {
        if (isAccountExist(signUpFormDto.getAccount())) {
            throw new SignUpException(ACCOUNT_EXIST);
        }
        //TODO email verify
        createNewPilot(signUpFormDto);
        return new SignUpResponseDto(SIGN_UP_SUCCESS_MESSAGE);
    }

    private void createNewPilot(SignUpFormDto signUpFormDto){
        String hashedPassword = bCryptPasswordEncoder.encode(signUpFormDto.getPassword());
        Pilot newPilot = Pilot.builder()
                .account(signUpFormDto.getAccount())
                .hashedPassword(hashedPassword)
                .email(signUpFormDto.getEmail())
                .name(signUpFormDto.getName())
                .phoneNumber(signUpFormDto.getPhoneNumber())
                .institution(signUpFormDto.getInstitution())
                .build();
        pilotRepository.save(newPilot);
    }

    private boolean isAccountExist(String account) {
        return verifyPilotExist(account);
    }

    @Override
    public Pilot getPilotByAccountId(long accountId) {
        return pilotRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT));
    }

    @Override
    public Pilot getPilotByAccount(String account) {
        return pilotRepository.findByAccount(account)
                .orElseThrow(() -> new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT));
    }

    @Override
    public boolean verifyPilotExist(String account) {
        return pilotRepository.findByAccount(account).isPresent();
    }

    @Override
    public List<Uav> getAllUavOfPilot(long accountId) {
        return uavRepository.findAllByPilotId(accountId);
    }

}
