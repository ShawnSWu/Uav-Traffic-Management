package com.nutn.utm.repository;

import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Repository
public interface FlightPlanRepository extends JpaRepository<FlightPlan, Long> {

    List<FlightPlan> findAllByUavPilotAndExecutionDateEquals(Pilot pilot, Date date);

    Optional<FlightPlan> findByUavPilotAndExecutionDateAndId(Pilot pilot, Date date, long id);

}
