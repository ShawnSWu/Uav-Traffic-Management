package com.nutn.utm.model;

import com.nutn.utm.utility.cayenne.type.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class UavRawData {

    private Accelerometer accelerometer;
    private Gps gps;
    private Gyrometer gyrometer;
    private NedCoordinate nedCoordinate;
    private Attitude attitude;

    public UavRawData(Gps gps, Gyrometer gyrometer, Accelerometer accelerometer, NedCoordinate nedCoordinate, Attitude attitude) {
        this.accelerometer = accelerometer;
        this.gps = gps;
        this.gyrometer = gyrometer;
        this.nedCoordinate = nedCoordinate;
        this.attitude = attitude;
    }

    public Accelerometer getAccelerometer() {
        return accelerometer;
    }

    public Gps getGps() {
        return gps;
    }

    public Gyrometer getGyrometer() {
        return gyrometer;
    }

    public NedCoordinate getNedCoordinate() {
        return nedCoordinate;
    }

    public Attitude getAttitude() {
        return attitude;
    }
}
