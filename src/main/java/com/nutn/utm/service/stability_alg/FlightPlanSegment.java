package com.nutn.utm.service.stability_alg;


public class FlightPlanSegment {

    FlightPlanWayPoint firstWayPoint;
    FlightPlanWayPoint secondWayPoint;
    double flightPlanSegmentDistance;

    public FlightPlanSegment(FlightPlanWayPoint firstWayPoint, FlightPlanWayPoint secondWayPoint, double flightPlanSegmentDistance) {
        this.firstWayPoint = firstWayPoint;
        this.secondWayPoint = secondWayPoint;
        this.flightPlanSegmentDistance = flightPlanSegmentDistance;
    }

    public FlightPlanWayPoint getFirstWayPoint() {
        return firstWayPoint;
    }

    public FlightPlanWayPoint getSecondWayPoint() {
        return secondWayPoint;
    }

    public double getFlightPlanSegmentDistance() {
        return flightPlanSegmentDistance;
    }
}
