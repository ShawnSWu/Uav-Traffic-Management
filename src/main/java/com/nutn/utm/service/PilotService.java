package com.nutn.utm.service;

import com.nutn.utm.model.entity.Pilot;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface PilotService {

    Pilot getPilotIfExists(long accountId);

    Pilot getPilotByAccount(String account);
}
