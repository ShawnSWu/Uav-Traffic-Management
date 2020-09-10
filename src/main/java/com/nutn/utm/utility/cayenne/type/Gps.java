package com.nutn.utm.utility.cayenne.type;


import java.util.Objects;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class Gps {
    private final double latitude;
    private final double longitude;
    private final double altitude;

    public Gps(final double latitude, final double longitude, final double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.altitude, this.latitude, this.longitude);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Gps other = (Gps) obj;

        return this.latitude - other.latitude < 0.0001 &&
                this.longitude - other.longitude < 0.0001 &&
                this.altitude - other.altitude < 0.01;
    }

    @Override
    public String toString() {
        return String.format("[GPS: lat: %s, lan: %s, alt: %s]", this.latitude, this.longitude, this.altitude);
    }

}