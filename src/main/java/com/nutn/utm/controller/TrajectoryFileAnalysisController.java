package com.nutn.utm.controller;

import com.nutn.utm.service.FlightFileReadService;
import com.nutn.utm.service.TrajectoryAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@RestController
@RequestMapping("/analysis")
public class TrajectoryFileAnalysisController {

    @Autowired
    TrajectoryAnalysisService trajectoryAnalysisService;

    @Autowired
    FlightFileReadService flightFileReadService;

    @PostMapping("/file/trajectory")
    public ResponseEntity<List<List<Double>>> readTrajectoryFile(@RequestParam("file") MultipartFile multipartfile) {
        List<List<Double>> trajectory = flightFileReadService.readTrajectoryCsvFile(multipartfile);
        return ResponseEntity.ok(trajectory);
    }

    @PostMapping("/file/wayPoints")
    public ResponseEntity<List<List<Double>>> readWayPointsFile(@RequestParam("file") MultipartFile multipartfile) {
        List<List<Double>> wayPoints = flightFileReadService.readWayPointsFile(multipartfile);
        return ResponseEntity.ok(wayPoints);
    }

    @PostMapping("/stability")
    public ResponseEntity<Double> analysisTrajectoryStability(@RequestParam("file") MultipartFile[] trajectoryAndFlightPlanFile) {
        List<List<Double>> trajectoryCsvFile = flightFileReadService.readTrajectoryCsvFile(trajectoryAndFlightPlanFile[0]);
        List<List<Double>> flightPlanFile = flightFileReadService.readWayPointsFile(trajectoryAndFlightPlanFile[1]);
        Double[][] flightPlanFileArrays = flightPlanFile.stream()
                .map(l -> l.toArray(new Double[0]))
                .toArray(Double[][]::new);
        return ResponseEntity.ok(trajectoryAnalysisService.analyzeTrajectoryStability(trajectoryCsvFile, flightPlanFileArrays));
    }
}
