package com.nutn.utm.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "flight_plan")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
