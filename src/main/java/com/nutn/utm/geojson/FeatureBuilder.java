package com.nutn.utm.geojson;

import com.google.gson.Gson;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class FeatureBuilder {

    private Feature feature;

    public FeatureBuilder buildPoint(double log, double lat){
        feature = Feature.fromGeometry(Point.fromLngLat(log, lat));
        return this;
    }

    public FeatureBuilder buildPoint(double log, double lat, Properties properties){
        feature = Feature.fromGeometry(Point.fromLngLat(log, lat));
        properties.forEach((k, v)-> feature.addStringProperty(String.valueOf(k), String.valueOf(v)));
        return this;
    }

    public FeatureBuilder buildLineString(List<List<Double>> linestring){
        List<Point> coordinateList = new ArrayList<>();
        linestring.forEach(data -> {
            coordinateList.add(Point.fromLngLat(data.get(0), data.get(1)));
        });
        feature = Feature.fromGeometry(LineString.fromLngLats(coordinateList));
        return this;
    }

    public FeatureBuilder buildLineString(List<List<Double>> linestring, Properties properties){
        List<Point> coordinateList = new ArrayList<>();
        linestring.forEach(data -> {
            coordinateList.add(Point.fromLngLat(data.get(0), data.get(1)));
        });
        feature = Feature.fromGeometry(LineString.fromLngLats(coordinateList));
        properties.forEach((k, v)-> feature.addStringProperty(String.valueOf(k), String.valueOf(v)));
        return this;
    }

    public FeatureBuilder buildPolygon(List<List<Point>> coordinates){

        return this;
    }


    public FeatureBuilder buildPolygon(List<List<Point>> coordinates, Properties properties){

        return this;
    }

    public FeatureBuilder buildMultiPoint(){

        return this;
    }

    public FeatureBuilder buildMultiLineString(){

        return this;
    }

    public FeatureBuilder buildMultiPolygon(){

        return this;
    }

    public String buildJson(){
        return feature.toJson();
    }

    public  <T> T  buildJsonObject(Class<T> dtoClass){
        return new Gson().fromJson(feature.toJson(), dtoClass);
    }


}
