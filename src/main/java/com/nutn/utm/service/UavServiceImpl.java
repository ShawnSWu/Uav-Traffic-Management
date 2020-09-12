package com.nutn.utm.service;

import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.repository.UavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Service
public class UavServiceImpl implements UavService {

    @Autowired
    UavRepository uavRepository;

    @Override
    public Uav getUavIfExists(String macAddress) {
        Uav uav = uavRepository.findByMacAddress(macAddress);
        if (!Optional.ofNullable(uav).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_UAV);
        return uav;
    }

    @Override
    public Uav checkIfPilotOwnsThisUav(long accountId, String macAddress) {
        Uav uav = uavRepository.findByPilotIdAndMacAddress(accountId, macAddress);
        if (!Optional.ofNullable(uav).isPresent())
            throw new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_UAV);
        return uav;
    }
}
