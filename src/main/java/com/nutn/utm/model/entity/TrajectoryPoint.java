package com.nutn.utm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "trajectory_point")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrajectoryPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private Date date;

    private Date time;

    private double latitude;

    private double longitude;

    private double altitude;

    private double yaw;

    private double roll;

    private double pitch;

    @Column(name = "gyro_x")
    private double gyroX;

    @Column(name = "gyro_y")
    private double gyroY;

    @Column(name = "gyro_z")
    private double gyroZ;

    @Column(name = "acc_x")
    private double accX;

    @Column(name = "acc_y")
    private double accY;

    @Column(name = "acc_z")
    private double accZ;

    @Column(name = "ned_north")
    private double nedNorth;

    @Column(name = "ned_east")
    private double nedEast;

    @Column(name = "ned_down")
    private double nedDown;

    @ManyToOne
    @JoinColumn(name = "flight_plan", referencedColumnName = "id")
    private FlightPlan flightPlan;

    private String hex_data_packet;
}
