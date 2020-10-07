package com.nutn.utm.service;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface TrajectoryAnalysisService {

    double analysisTrajectoryStability(List<List<Double>> trajectorys, double[][] flightPlan);
}
