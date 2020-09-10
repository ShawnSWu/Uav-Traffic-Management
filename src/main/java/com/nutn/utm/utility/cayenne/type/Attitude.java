package com.nutn.utm.utility.cayenne.type;

import java.util.Objects;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class Attitude {
    private final double pitch;
    private final double yaw;
    private final double roll;

    public Attitude(final double pitch, final double yaw, final double roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public double getPitch() {
        return this.pitch;
    }

    public double getYaw() {
        return this.yaw;
    }

    public double getRoll() {
        return this.roll;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pitch, this.yaw, this.roll);
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
        final Attitude other = (Attitude) obj;

        return this.pitch - other.pitch < 0.01 &&
                this.yaw - other.yaw < 0.01 &&
                this.roll - other.roll < 0.01;

    }

    @Override
    public String toString() {
        return String.format("[Attitude: pitch: %s, yaw: %s, roll: %s]", this.pitch, this.yaw, this.roll);
    }
}