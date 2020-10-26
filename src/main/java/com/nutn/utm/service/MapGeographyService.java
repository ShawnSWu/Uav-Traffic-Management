package com.nutn.utm.service;

import com.nutn.utm.model.dto.geojson.geography.GeographyLimitAreaFeatureCollection;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface MapGeographyService {

    byte[] getForbidArea();

    GeographyLimitAreaFeatureCollection getForbidAreaDto();

    byte[] getRestrictAreaAirport();

    byte[] getRestrictAreaMilitaryCamp();

}
