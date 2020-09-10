package com.nutn.utm.utility.cayenne.type;

import java.util.Objects;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class NedCoordinate {
    private final double north;
    private final double east;
    private final double down;

    public NedCoordinate(final double north, final double east, final double down) {
        this.north = north;
        this.east = east;
        this.down = down;
    }

    public double getNorth() {
        return this.north;
    }

    public double getEast() {
        return this.east;
    }

    public double getDown() {
        return this.down;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.north, this.east, this.down);
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
        final NedCoordinate other = (NedCoordinate) obj;

        return this.north - other.north < 0.01 &&
                this.east - other.east < 0.01 &&
                this.down - other.down < 0.01;

    }

    @Override
    public String toString() {
        return String.format("[NED: north: %s, east: %s, down: %s]", this.north, this.east, this.down);
    }
}