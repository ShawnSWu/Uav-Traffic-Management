package com.nutn.utm.service;

import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Service
public class PilotServiceImpl implements PilotService {

    @Autowired
    PilotRepository pilotRepository;

    @Override
    public Pilot getPilotIfExists(long accountId){
        Pilot pilot = pilotRepository.findById(accountId);
        if (!Optional.ofNullable(pilot).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT);
        return pilot;
    }

    @Override
    public Pilot getPilotByAccount(String account) {
        Pilot pilot = pilotRepository.findByAccount(account);
        if (!Optional.ofNullable(pilot).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT);
        return pilot;
    }


}
