package com.nutn.utm.service.stability_alg;

public abstract class WayPoint {

    private double latitude;

    private double longitude;

    public WayPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
