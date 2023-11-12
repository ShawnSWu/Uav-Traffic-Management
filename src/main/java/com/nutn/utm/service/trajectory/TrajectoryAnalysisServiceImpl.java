package com.nutn.utm.service.trajectory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.service.stability_alg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TrajectoryAnalysisServiceImpl implements TrajectoryAnalysisService {

    TrajectoryAnalyzer trajectoryAnalyzer = new TrajectoryAnalyzer();

    @Autowired
    ObjectMapper objectMapper;

    public double analyzeTrajectoryStability(List<List<Double>> trajectorys, Double[][] flightPlan) {
        List<TrajectorySegment> trajectorySegments = new ArrayList<>();

        for (int i = 0; i < trajectorys.size(); i++) {
            if (i + 1 < trajectorys.size()) {
                TrajectoryPoint firstPoint = new TrajectoryPoint(trajectorys.get(i).get(0), trajectorys.get(i).get(1));
                TrajectoryPoint secondPoint = new TrajectoryPoint(trajectorys.get(i + 1).get(0), trajectorys.get(i + 1).get(1));

                FlightPlanSegment firstPointVerticalOnFlightPlan = trajectoryAnalyzer.getFlightPlanSegmentByTrajectoryPoint(firstPoint, flightPlan);
                FlightPlanSegment secondPointVerticalOnFlightPlan = trajectoryAnalyzer.getFlightPlanSegmentByTrajectoryPoint(secondPoint, flightPlan);

                FlightPlanWayPoint verticalFirstPoint = trajectoryAnalyzer.getVerticalProjectedOnFlightPlanPoint(firstPoint, firstPointVerticalOnFlightPlan);
                FlightPlanWayPoint verticalSecondPoint = trajectoryAnalyzer.getVerticalProjectedOnFlightPlanPoint(secondPoint, secondPointVerticalOnFlightPlan);

                List<TrajectoryPoint> trajectoryPoints = Arrays.asList(firstPoint, secondPoint);
                List<FlightPlanWayPoint> verticalOnFlightPlanPoints = Arrays.asList(verticalFirstPoint, verticalSecondPoint);

                trajectorySegments.add(new TrajectorySegment(trajectoryPoints, verticalOnFlightPlanPoints,
                        firstPointVerticalOnFlightPlan, secondPointVerticalOnFlightPlan));
            }
        }

        double totalStabilityDegree = 0.0;

        int item = 0;
        for (TrajectorySegment trajectorySegment : trajectorySegments) {
            double cosineSimilarity = trajectoryAnalyzer.computeSimilarity(trajectorySegment.getVerticalProjectedOnFlightPlanPoints(), trajectorySegment.getTrajectoryPoints());
            double distanceGrade = trajectoryAnalyzer.computeDistanceStability(trajectorySegment);

            double cosineSimilarityFormula = (((1 + cosineSimilarity) / 2) * 0.2);
            double distanceGradeFormula = distanceGrade * 0.8;

            Double segmentStabilityDegree = cosineSimilarityFormula + distanceGradeFormula;

            if (!segmentStabilityDegree.isNaN()) {
                totalStabilityDegree += Double.parseDouble(new DecimalFormat("##.00000").format(segmentStabilityDegree));
                item++;
            }
        }

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);
        return Double.parseDouble(nf.format((totalStabilityDegree / item)));
    }
}
