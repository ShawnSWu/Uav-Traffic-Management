package com.nutn.utm.service.flight_plan;

import com.nutn.utm.model.dto.geojson.geography.GeographyLimitAreaFeature;
import com.nutn.utm.model.dto.response.error.FlightPlanWayPointVerifyResultDto;

import java.util.List;

import static com.nutn.utm.model.dto.response.message.ValidationMessage.PLAN_WAY_POINT_ILLEGAL;
import static com.nutn.utm.model.dto.response.message.ValidationMessage.PLAN_WAY_POINT_LEGITIMATE;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class FlightPlanWayPointValidator {

    public FlightPlanWayPointVerifyResultDto verifyPlanWayPointLegality(Double[][] planCoordinate, List<GeographyLimitAreaFeature> forbidAreaFeatures) {
        for (GeographyLimitAreaFeature forbidAreaFeature : forbidAreaFeatures) {
            List<List<Double>> forbidAreaCoordinate = forbidAreaFeature.getGeometry().getCoordinates().get(0);
            if (isPathCrossForbidArea(planCoordinate, forbidAreaCoordinate)) {
                String verifyResult = String.format(PLAN_WAY_POINT_ILLEGAL, forbidAreaFeature.getProperties().getDescription());
                return FlightPlanWayPointVerifyResultDto.builder()
                        .isApprovedApply(false)
                        .verifyResultMessage(verifyResult)
                        .build();
            }
        }
        return FlightPlanWayPointVerifyResultDto.builder().isApprovedApply(true).verifyResultMessage(PLAN_WAY_POINT_LEGITIMATE).build();
    }

    private boolean isPathCrossForbidArea(Double[][] planCoordinate, List<List<Double>> forbidCoordinate) {
        for (int i = 0; i < forbidCoordinate.size() - 1; i++) {
            double x1, y1, x2, y2, x3, y3, x4, y4;
            for (int j = 0; j < planCoordinate.length - 1; j++) {
                x1 = planCoordinate[j][1];
                y1 = planCoordinate[j][0];

                x2 = planCoordinate[j + 1][1];
                y2 = planCoordinate[j + 1][0];

                x3 = forbidCoordinate.get(i).get(1);
                y3 = forbidCoordinate.get(i).get(0);

                x4 = forbidCoordinate.get(i + 1).get(1);
                y4 = forbidCoordinate.get(i + 1).get(0);

                if (isPathCrossForbidArea(y1, x1, y2, x2, y3, x3, y4, x4)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Adapted from https://en.wikipedia.org/wiki/Line%E2%80%93line_intersection
    private boolean isPathCrossForbidArea(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        double commonDenominator = (x2 - x1) * (y4 - y3) - (y2 - y1) * (x4 - x3);
        if (commonDenominator == 0) {
            return false;
        }
        double t = ((x3 - x1) * (y4 - y3) - (y3 - y1) * (x4 - x3)) / commonDenominator;
        if (t < 0 || t > 1) {
            return false;
        }
        double u = ((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) / commonDenominator;
        if (u < 0 || u > 1) {
            return false;
        }
        return true;

    }
}
