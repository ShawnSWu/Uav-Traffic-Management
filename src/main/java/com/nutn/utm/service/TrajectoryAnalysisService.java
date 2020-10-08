package com.nutn.utm.service;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface TrajectoryAnalysisService {

    double analyzeTrajectoryStability(List<List<Double>> trajectorys, Double[][] flightPlan);
}
