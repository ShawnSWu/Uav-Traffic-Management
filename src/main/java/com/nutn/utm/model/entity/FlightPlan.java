package com.nutn.utm.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "flight_plan")
public class FlightPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "uav", referencedColumnName = "id")
    private Uav uav;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "execution_date")
    private Date executionDate;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date startTime;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date endTime;

    private int maxFlyingAltitude;

    @Column(name = "flight_plan_waypoints")
    private String flightPlanWayPoints;

    private String description;

    public FlightPlan(Uav uav, Date executionDate, Date startTime, Date endTime,
                      int maxFlyingAltitude, String flightPlanWaypoints, String description) {
        this.uav = uav;
        this.executionDate = executionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxFlyingAltitude = maxFlyingAltitude;
        this.flightPlanWayPoints = flightPlanWaypoints;
        this.description = description;
    }

    public FlightPlan() {
    }

    public long getId() {
        return id;
    }


    public Uav getUav() {
        return uav;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public String getFlightPlanWayPoints() {
        return flightPlanWayPoints;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getMaxFlyingAltitude() {
        return maxFlyingAltitude;
    }

    public String getDescription() {
        return description;
    }

    public void setUav(Uav uav) {
        this.uav = uav;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setMaxFlyingAltitude(int maxFlyingAltitude) {
        this.maxFlyingAltitude = maxFlyingAltitude;
    }

    public void setFlightPlanWayPoints(String flightPlanWayPoints) {
        this.flightPlanWayPoints = flightPlanWayPoints;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }
}
