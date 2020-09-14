package com.nutn.utm.repository;

import com.nutn.utm.model.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Repository
public interface PilotRepository extends JpaRepository<Pilot, Long> {

    Optional<Pilot> findById(long accountId);

    Optional<Pilot> findByAccount(String account);
}
