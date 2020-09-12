package com.nutn.utm.repository;

import com.nutn.utm.model.entity.FlightPlan;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.model.entity.Uav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("from flight_plan where uav =?1 and executionDate=?2 and startTime <= ?3 and endTime >= ?3")
    Optional<FlightPlan> findByMacAddressAndExecutionDateAndBetweenStartAndEndTime(Uav uav, Date date, Date time);

    @Query("from flight_plan where uav.pilot.id =?1 and executionDate=?2 and startTime <= ?3 and endTime >= ?3")
    List<FlightPlan> findAllByPilotIdAndExecutionDateAndBetweenStartAndEndTime(long account, Date date, Date time);

    List<FlightPlan> findAllByUavPilotIdAndExecutionDateAndEndTimeLessThanEqual(long account, Date date, Date time);

    List<FlightPlan> findAllByUavPilotIdAndExecutionDateAndStartTimeGreaterThan(long account, Date date, Date time);

    FlightPlan findByUavAndExecutionDateEqualsAndEndTimeGreaterThanEqualAndStartTimeLessThanEqual(
            Uav uav, Date date, Date endTime, Date startTime);
}
