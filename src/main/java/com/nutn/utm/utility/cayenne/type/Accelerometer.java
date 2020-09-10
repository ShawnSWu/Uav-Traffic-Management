package com.nutn.utm.utility.cayenne.type;

import java.util.Objects;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class Accelerometer {
    private final double x;
    private final double y;
    private final double z;

    public Accelerometer(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y, this.z);
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
        final Accelerometer other = (Accelerometer) obj;

        return this.x - other.x < 0.01 &&
                this.y - other.y < 0.01 &&
                this.z - other.z < 0.01;

    }

    @Override
    public String toString() {
        return String.format("[Accelerometer: x: %s, y: %s, z: %s]", this.x, this.y, this.z);
    }
}