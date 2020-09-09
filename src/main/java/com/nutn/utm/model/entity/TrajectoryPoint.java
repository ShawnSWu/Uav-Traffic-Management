package com.nutn.utm.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Entity(name = "trajectory_point")
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

    private double gyroX;

    private double gyroY;

    private double gyroZ;

    private double volt;

    private double hex_data_packet;

    public TrajectoryPoint(long id, Date date, Date time, double latitude, double longitude, double altitude,
                           double yaw, double roll, double pitch, double gyroX, double gyroY, double gyroZ,
                           double volt, double hex_data_packet) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.yaw = yaw;
        this.roll = roll;
        this.pitch = pitch;
        this.gyroX = gyroX;
        this.gyroY = gyroY;
        this.gyroZ = gyroZ;
        this.volt = volt;
        this.hex_data_packet = hex_data_packet;
    }

    public TrajectoryPoint() {
    }

    public long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getYaw() {
        return yaw;
    }

    public double getRoll() {
        return roll;
    }

    public double getPitch() {
        return pitch;
    }

    public double getGyroX() {
        return gyroX;
    }

    public double getGyroY() {
        return gyroY;
    }

    public double getGyroZ() {
        return gyroZ;
    }

    public double getVolt() {
        return volt;
    }

    public double getHex_data_packet() {
        return hex_data_packet;
    }
}
