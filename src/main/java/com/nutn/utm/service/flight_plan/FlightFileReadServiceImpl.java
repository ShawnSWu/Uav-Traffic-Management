package com.nutn.utm.service.flight_plan;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Service
public class FlightFileReadServiceImpl implements FlightFileReadService{


    @Override
    public List<List<Double>> readTrajectoryCsvFile(MultipartFile csvFile) {
        List<List<Double>> trajectory = new ArrayList<>();
        try {
            InputStream inputStream = csvFile.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] item = line.split(",");
                String longitude = item[2].trim();
                String latitude = item[3].trim();
                trajectory.add(Arrays.asList(Double.parseDouble(longitude), Double.parseDouble(latitude)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trajectory;
    }

    @Override
    public List<List<Double>> readWayPointsFile(MultipartFile wayPointsFile) {
        List<List<Double>> wayPoints = new ArrayList<>();
        try {
            InputStream inputStream = wayPointsFile.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] item = line.split("\t");
                String id = item[0].trim();
                if(!id.equals("0")) {
                    String longitude = item[8].trim();
                    String latitude = item[9].trim();
                    wayPoints.add(Arrays.asList(Double.parseDouble(longitude), Double.parseDouble(latitude)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wayPoints;
    }
}
