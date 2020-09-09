package com.nutn.utm.exception;

import com.mapbox.geojson.exception.GeoJsonException;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class PolygonFormatException extends GeoJsonException {

    public PolygonFormatException() {
        super("Every Polygon's first and last position must be the same.");
    }

}
