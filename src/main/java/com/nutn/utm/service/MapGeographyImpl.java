package com.nutn.utm.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Service
public class MapGeographyImpl implements MapGeography{

    @Override
    public byte[] getForbidArea() {
        return getResourceFile("static/forbid_area_airport.json");
    }

    @Override
    public byte[] getRestrictAreaAirport() {
        return getResourceFile("static/restrict_area_airport_nearby.json");
    }

    @Override
    public byte[] getRestrictAreaMilitaryCamp() {
        return getResourceFile("static/forbid_area_military_camp.json");
    }

    private byte[] getResourceFile(String path) {
        Resource resource = new ClassPathResource(path);
        byte[] bdata =null;
        try {
            bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bdata;
    }

}
