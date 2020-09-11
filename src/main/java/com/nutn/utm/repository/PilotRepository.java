package com.nutn.utm.repository;

import com.nutn.utm.model.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {

    Pilot findById(long accountId);

    Pilot findByAccount(String account);
}
