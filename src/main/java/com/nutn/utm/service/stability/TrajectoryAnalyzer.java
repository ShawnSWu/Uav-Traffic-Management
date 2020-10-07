package com.nutn.utm.service.stability;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

import java.util.List;

import static java.lang.Math.sqrt;

public class TrajectoryAnalyzer {

    private static final int FENCE_MAX = 3;
    private static final double apsolon = 0.000001;

    private static final double alpha = 0.4;
    private static final double beta = 0.3;
    private static final double gamma = 0.3;


    public double computeSimilarity(List<FlightPlanWayPoint> verticalProjectedFlightPlanSegment, List<TrajectoryPoint> trajectorySegment) {
        TrajectoryPoint l0 = trajectorySegment.get(0);
        TrajectoryPoint l1 = trajectorySegment.get(1);

        FlightPlanWayPoint p0 = verticalProjectedFlightPlanSegment.get(0);
        FlightPlanWayPoint p1 = verticalProjectedFlightPlanSegment.get(1);

        RealVector vectorL = new ArrayRealVector(new Double[]{l1.getLatitude() - l0.getLatitude(), l1.getLongitude() - l0.getLongitude(), 0.0});
        RealVector vectorP = new ArrayRealVector(new Double[]{p1.getLatitude() - p0.getLatitude(), p1.getLongitude() - p0.getLongitude(), 0.0});

        double vectorDotProduct = vectorL.dotProduct(vectorP);
        double vectorDistance = vectorL.getNorm() * vectorP.getNorm();

        return vectorDotProduct / vectorDistance;
    }

    public double computeDistanceStability(TrajectorySegment trajectorySegment) {
        TrajectoryPoint l1 = new TrajectoryPoint(trajectorySegment.getTrajectoryPoints().get(1).getLatitude(), trajectorySegment.getTrajectoryPoints().get(1).getLongitude());
        FlightPlanWayPoint p1 = new FlightPlanWayPoint(trajectorySegment.getVerticalProjectedOnFlightPlanPoints().get(1).getLatitude(), trajectorySegment.getVerticalProjectedOnFlightPlanPoints().get(1).getLongitude());
        TrajectoryPoint l0 = new TrajectoryPoint(trajectorySegment.getTrajectoryPoints().get(0).getLatitude(), trajectorySegment.getTrajectoryPoints().get(0).getLongitude());
        FlightPlanWayPoint p0 = new FlightPlanWayPoint(trajectorySegment.getVerticalProjectedOnFlightPlanPoints().get(0).getLatitude(), trajectorySegment.getVerticalProjectedOnFlightPlanPoints().get(0).getLongitude());

        double l1ToP1 = Haversine.distance(l1, p1);
        double l0ToP0 = Haversine.distance(l0, p0);
        double l1ToL0 = Haversine.distance(l1, l0);
        double fRoute;

        double p0ToS1 = Haversine.distance(p0, trajectorySegment.getFirstPointVerticalProjectedFlightPlan().getSecondWayPoint());

        if (trajectorySegment.getFirstPointVerticalProjectedFlightPlan().getFirstWayPoint().getLongitude()
                == trajectorySegment.getSecondPointVerticalProjectedFlightPlan().getFirstWayPoint().getLongitude()) {
            fRoute = p0ToS1;
        } else {
            double s1ToP1 = Haversine.distance(trajectorySegment.getFirstPointVerticalProjectedFlightPlan().getFirstWayPoint(), p1);
            fRoute = p0ToS1 + s1ToP1;
        }

        double part1MainFormula = (1 - (l1ToP1 / FENCE_MAX));
        double part1 = (part1MainFormula <= 0) ? apsolon : part1MainFormula + apsolon;

        double part2MainFormula = (1 - (Math.abs(l1ToP1 - l0ToP0) / FENCE_MAX));
        double part2EntireFormula = part2MainFormula + apsolon;
        double part2 = 1;

        if ((l1ToP1 - l0ToP0) != 0) {
            part2 = (part2MainFormula < 0) ? apsolon : part2EntireFormula;
        }

        double part3 = Math.min(l1ToL0, fRoute) / Math.max(l1ToL0, fRoute);

        return ((part1 * alpha) + (part2 * beta) + (part3 * gamma));
    }

    public FlightPlanSegment getFlightPlanSegmentByTrajectoryPoint(TrajectoryPoint trajectoryPoint, List<List<Double>> flightPlan) {
        FlightPlanWayPoint firstWayPointFlightPlan = null;
        FlightPlanWayPoint SecondWayPointFlightPlan = null;
        double verticalDistance = Double.MAX_VALUE;
        for (int i = 0; i < flightPlan.size(); i++) {
            if (i + 1 < flightPlan.size()) {
                FlightPlanWayPoint planFirstWayPoint = new FlightPlanWayPoint(flightPlan.get(i).get(0), flightPlan.get(i).get(1));
                FlightPlanWayPoint planSecondWayPoint = new FlightPlanWayPoint(flightPlan.get(i + 1).get(0), flightPlan.get(i + 1).get(1));

                double h = getVerticalDistanceByPointToFlightPlan(planFirstWayPoint, planSecondWayPoint, trajectoryPoint);
                if (h < verticalDistance) {
                    verticalDistance = h;
                    firstWayPointFlightPlan = planFirstWayPoint;
                    SecondWayPointFlightPlan = planSecondWayPoint;
                }
            }
        }
        return new FlightPlanSegment(firstWayPointFlightPlan, SecondWayPointFlightPlan, verticalDistance);
    }

    public FlightPlanSegment getFlightPlanSegmentByTrajectoryPoint(TrajectoryPoint trajectoryPoint, double[][] flightPlan) {
        FlightPlanWayPoint firstWayPointFlightPlan = null;
        FlightPlanWayPoint SecondWayPointFlightPlan = null;
        double verticalDistance = Double.MAX_VALUE;
        for (int i = 0; i < flightPlan.length; i++) {
            if (i + 1 < flightPlan.length) {
                FlightPlanWayPoint planFirstWayPoint = new FlightPlanWayPoint(flightPlan[i][0], flightPlan[i][1]);
                FlightPlanWayPoint planSecondWayPoint = new FlightPlanWayPoint(flightPlan[i + 1][0], flightPlan[i + 1][1]);

                double h = getVerticalDistanceByPointToFlightPlan(planFirstWayPoint, planSecondWayPoint, trajectoryPoint);
                if (h < verticalDistance) {
                    verticalDistance = h;
                    firstWayPointFlightPlan = planFirstWayPoint;
                    SecondWayPointFlightPlan = planSecondWayPoint;
                }
            }
        }
        return new FlightPlanSegment(firstWayPointFlightPlan, SecondWayPointFlightPlan, verticalDistance);
    }

    public double getVerticalDistanceByPointToFlightPlan(FlightPlanWayPoint planFirstWayPoint, FlightPlanWayPoint planSecondWayPoint, TrajectoryPoint trajectoryPoint) {
        double flightPlanDistance = Haversine.distance(planFirstWayPoint, planSecondWayPoint);
        double planFirstWayPointToTrajectoryPointDistance = Haversine.distance(trajectoryPoint, planFirstWayPoint);
        double planSecondWayPointToTrajectoryPointDistance = Haversine.distance(trajectoryPoint, planSecondWayPoint);
        double area = heronFormula(flightPlanDistance, planFirstWayPointToTrajectoryPointDistance, planSecondWayPointToTrajectoryPointDistance);
        return (area / flightPlanDistance) * 2;

    }

    public double heronFormula(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public FlightPlanWayPoint getVerticalProjectedOnFlightPlanPoint(TrajectoryPoint trajectoryPoint, FlightPlanSegment flightPlanSegment) {
        double[] trajectoryXyz = GpsEcefConvertor.getXYZfromLatLonDegrees(trajectoryPoint.getLatitude(), trajectoryPoint.getLongitude(), 0);
        double cX = trajectoryXyz[0];
        double cY = trajectoryXyz[1];
        double cZ = trajectoryXyz[2];

        double[] planFirstPoint = GpsEcefConvertor.getXYZfromLatLonDegrees(flightPlanSegment.getFirstWayPoint().getLatitude(), flightPlanSegment.getFirstWayPoint().getLongitude(), 0);
        double aX = planFirstPoint[0];
        double aY = planFirstPoint[1];
        double aZ = planFirstPoint[2];

        double[] planSecondPoint = GpsEcefConvertor.getXYZfromLatLonDegrees(flightPlanSegment.getSecondWayPoint().getLatitude(), flightPlanSegment.getSecondWayPoint().getLongitude(), 0);
        double bX = planSecondPoint[0];
        double bY = planSecondPoint[1];
        double bZ = planSecondPoint[2];

        double t = ((bX - aX) * (cX - aX) + (bY - aY) * (cY - aY) + (bZ - aZ) * (cZ - aZ)) / (Math.pow((bX - aX), 2) + Math.pow((bY - aY), 2) + Math.pow((bZ - aZ), 2));

        double[] p = {aX + (bX - aX) * t, aY + (bY - aY) * t, aZ + (bZ - aZ) * t};

        double[] gps = GpsEcefConvertor.xyzToLatLonDegrees(new double[]{p[0], p[1], p[2]});

        return new FlightPlanWayPoint(gps[0], gps[1]);
    }

}
