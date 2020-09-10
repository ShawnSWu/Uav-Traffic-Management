package com.nutn.utm.utility.cayenne.type;

import java.util.Objects;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class Gyrometer {
    private final double x;
    private final double y;
    private final double z;

    public Gyrometer(final double x, final double y, final double z) {
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
        final Gyrometer other = (Gyrometer) obj;
        return this.x - other.x < 0.001 &&
                this.y - other.y < 0.001 &&
                this.z - other.z < 0.001;
    }

    @Override
    public String toString() {
        return String.format("[Gyrometer: x: %s, y: %s, z: %s]", this.x, this.y, this.z);
    }

}