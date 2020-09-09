package com.nutn.utm.utility.geojson;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class GeoJsonTool {

    public static FeatureCollectionBuilder buildFeatureCollection(){
        return new FeatureCollectionBuilder();
    }

    public static FeatureBuilder buildFeature(){
        return new FeatureBuilder();
    }


}
