package com.nutn.utm.repository;

import com.nutn.utm.model.entity.Uav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Repository
public interface UavRepository extends JpaRepository<Uav, Long> {

    List<Uav> findAllByPilotAccount(String pilotAccount);

    Uav findByMacAddress(String macAddress);

}
