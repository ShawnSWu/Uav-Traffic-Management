package com.nutn.utm.service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface FlightFileReadService {

    List<List<Double>> readTrajectoryCsvFile(MultipartFile csvFile);

    List<List<Double>> readWayPointsFile(MultipartFile wayPointsFile);
}
