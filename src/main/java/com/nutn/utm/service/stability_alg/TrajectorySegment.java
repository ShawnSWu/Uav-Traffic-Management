package com.nutn.utm.service.stability_alg;

import java.util.List;

public class TrajectorySegment {

    private List<TrajectoryPoint> trajectoryPoints;

    private List<FlightPlanWayPoint> verticalProjectedOnFlightPlanPoints;

    private FlightPlanSegment firstPointVerticalProjectedFlightPlan;

    private FlightPlanSegment secondPointVerticalProjectedFlightPlan;


    public TrajectorySegment(List<TrajectoryPoint> trajectoryPoints, List<FlightPlanWayPoint> verticalProjectedOnFlightPlanPoints,
                             FlightPlanSegment firstPointVerticalProjectedFlightPlan, FlightPlanSegment secondPointVerticalProjectedFlightPlan) {
        this.trajectoryPoints = trajectoryPoints;
        this.verticalProjectedOnFlightPlanPoints = verticalProjectedOnFlightPlanPoints;
        this.firstPointVerticalProjectedFlightPlan = firstPointVerticalProjectedFlightPlan;
        this.secondPointVerticalProjectedFlightPlan = secondPointVerticalProjectedFlightPlan;
    }

    public List<TrajectoryPoint> getTrajectoryPoints() {
        return trajectoryPoints;
    }

    public List<FlightPlanWayPoint> getVerticalProjectedOnFlightPlanPoints() {
        return verticalProjectedOnFlightPlanPoints;
    }

    public FlightPlanSegment getFirstPointVerticalProjectedFlightPlan() {
        return firstPointVerticalProjectedFlightPlan;
    }

    public FlightPlanSegment getSecondPointVerticalProjectedFlightPlan() {
        return secondPointVerticalProjectedFlightPlan;
    }
}
