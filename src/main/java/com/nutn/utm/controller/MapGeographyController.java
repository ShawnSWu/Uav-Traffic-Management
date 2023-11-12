package com.nutn.utm.controller;

import com.nutn.utm.model.dto.geojson.geography.GeographyLimitAreaFeatureCollection;
import com.nutn.utm.service.trajectory.MapGeographyService;
import com.nutn.utm.utility.geojson.GeoJsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@CrossOrigin
@RestController
@RequestMapping("/mapGeography")
public class MapGeographyController {

    @Autowired
    private MapGeographyService mapGeographyService;

    @Autowired
    private GeoJsonConverter geoJsonConverter;

    @GetMapping(value = "/forbidArea/airport")
    public GeographyLimitAreaFeatureCollection getForbidAreaGeoJson(){
        return geoJsonConverter.convertAreaToGeographyCollection(mapGeographyService.getForbidArea());
    }

    @GetMapping(value = "/restrictArea/airportNearby")
    public GeographyLimitAreaFeatureCollection getRestrictAreaAirportGeoJson(){
        return geoJsonConverter.convertAreaToGeographyCollection(mapGeographyService.getRestrictAreaAirport());
    }

    @GetMapping(value = "/forbidArea/militaryCamp")
    public GeographyLimitAreaFeatureCollection getRestrictAreaMilitaryCampGeoJson(){
        return geoJsonConverter.convertAreaToGeographyCollection(mapGeographyService.getRestrictAreaMilitaryCamp());
    }


}
