package com.nutn.utm.service.stability;


public class GpsEcefConvertor {

    public static final double RADIANS_TO_DEGREES = 180.0 / Math.PI;
    public static final double DEGREES_TO_RADIANS = Math.PI / 180.0;

    private GpsEcefConvertor() {
    }

    public static double[] xyzToLatLonRadians(double[] xyz) {
        double x = xyz[0];
        double y = xyz[1];
        double z = xyz[2];
        double answer[] = new double[3];
        double a = 6378137.0; //semi major axis
        double b = 6356752.3142; //semi minor axis

        double eSquared; //first eccentricity squared
        double rSubN; //radius of the curvature of the prime vertical
        double ePrimeSquared;//second eccentricity squared
        double W = Math.sqrt((x * x + y * y));

        eSquared = (a * a - b * b) / (a * a);
        ePrimeSquared = (a * a - b * b) / (b * b);

        if (x >= 0) {
            answer[1] = Math.atan(y / x);
        } else if (x < 0 && y >= 0) {
            answer[1] = Math.atan(y / x) + Math.PI;
        } else {
            answer[1] = Math.atan(y / x) - Math.PI;
        }

        double tanBZero = (a * z) / (b * W);
        double BZero = Math.atan((tanBZero));
        double tanPhi = (z + (ePrimeSquared * b * (Math.pow(Math.sin(BZero), 3)))) / (W - (a * eSquared * (Math.pow(Math.cos(BZero), 3))));
        double phi = Math.atan(tanPhi);
        answer[0] = phi;
        rSubN = (a * a) / Math.sqrt(((a * a) * (Math.cos(phi) * Math.cos(phi)) + ((b * b) * (Math.sin(phi) * Math.sin(phi)))));

        answer[2] = (W / Math.cos(phi)) - rSubN;

        return answer;
    }

    public static double[] xyzToLatLonDegrees(double[] xyz) {
        double degrees[] = GpsEcefConvertor.xyzToLatLonRadians(xyz);

        degrees[0] = degrees[0] * GpsEcefConvertor.RADIANS_TO_DEGREES;
        degrees[1] = degrees[1] * GpsEcefConvertor.RADIANS_TO_DEGREES;

        return degrees;

    }

    public static double[] getXYZfromLatLonRadians(double latitude, double longitude, double height) {
        double a = 6378137.0; //semi major axis
        double b = 6356752.3142; //semi minor axis
        double cosLat = Math.cos(latitude);
        double sinLat = Math.sin(latitude);


        double rSubN = (a * a) / Math.sqrt(((a * a) * (cosLat * cosLat) + ((b * b) * (sinLat * sinLat))));

        double X = (rSubN + height) * cosLat * Math.cos(longitude);
        double Y = (rSubN + height) * cosLat * Math.sin(longitude);
        double Z = ((((b * b) / (a * a)) * rSubN) + height) * sinLat;

        return new double[]{X, Y, Z};
    }

    public static double[] getXYZfromLatLonDegrees(double latitude, double longitude, double height) {
        double degrees[] = GpsEcefConvertor.getXYZfromLatLonRadians(latitude * GpsEcefConvertor.DEGREES_TO_RADIANS,
                longitude * GpsEcefConvertor.DEGREES_TO_RADIANS,
                height);

        return degrees;
    }
}
