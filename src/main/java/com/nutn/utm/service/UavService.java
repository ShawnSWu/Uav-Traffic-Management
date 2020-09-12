package com.nutn.utm.service;

import com.nutn.utm.model.entity.Uav;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface UavService {

    Uav getUavIfExists(String macAddress);

    Uav checkIfPilotOwnsThisUav(long accountId, String macAddress);
}
