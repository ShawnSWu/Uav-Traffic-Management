package com.nutn.utm.service;

import com.nutn.utm.exception.NotFoundFlightPlanException;
import com.nutn.utm.model.dto.response.message.ApiExceptionMessage;
import com.nutn.utm.model.entity.Uav;
import com.nutn.utm.repository.UavRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Service
public class UavServiceImpl implements UavService {

    @Autowired
    UavRepository uavRepository;

    @Override
    public Uav getUavIfExists(String macAddress) {
        return uavRepository.findByMacAddress(macAddress)
                .orElseThrow(() -> new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_UAV));
    }

    @Override
    public Uav checkIfPilotOwnsThisUav(long accountId, String macAddress) {
        return uavRepository.findByPilotIdAndMacAddress(accountId, macAddress)
                .orElseThrow(() -> new NotFoundFlightPlanException(ApiExceptionMessage.NOT_FOUND_UAV));
    }
}
