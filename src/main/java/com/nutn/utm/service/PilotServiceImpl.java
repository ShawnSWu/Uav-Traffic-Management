package com.nutn.utm.service;

import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.repository.PilotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Service
public class PilotServiceImpl implements PilotService {

    @Autowired
    PilotRepository pilotRepository;

    @Override
    public Pilot getPilotIfExists(long accountId){
        return pilotRepository.findById(accountId)
                .orElseThrow(()-> new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT));
    }

    @Override
    public Pilot getPilotByAccount(String account) {
        return pilotRepository.findByAccount(account)
                .orElseThrow(() -> new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_PILOT));
    }


}
