package com.nutn.utm.utility.cayenne;

import com.nutn.utm.model.UavSensingData;
import com.nutn.utm.utility.cayenne.decoder.*;
import com.nutn.utm.utility.cayenne.type.*;

import java.nio.ByteBuffer;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class CayenneLPPDataParser {

    public static UavSensingData parser(String cayenneHexString){
        String gpsHex = cayenneHexString.substring(4,22);
        String gyrometerHex = cayenneHexString.substring(26,38);
        String accelerometerHex = cayenneHexString.substring(42,54);
        String nedHex = cayenneHexString.substring(58,70);
        String attitudeHex = cayenneHexString.substring(74,86);

        byte[] gpsHexBytes = hexStringToByteArray(gpsHex);
        byte[] gyrometerHexBytes = hexStringToByteArray(gyrometerHex);
        byte[] accelerometerHexBytes = hexStringToByteArray(accelerometerHex);
        byte[] nedHexBytes = hexStringToByteArray(nedHex);
        byte[] attitudeHexBytes = hexStringToByteArray(attitudeHex);

        ByteBuffer gpsHexByteBuffer = ByteBuffer.wrap(gpsHexBytes);
        ByteBuffer gyrometerHexByteBuffer = ByteBuffer.wrap(gyrometerHexBytes);
        ByteBuffer accelerometerHexByteBuffer = ByteBuffer.wrap(accelerometerHexBytes);
        ByteBuffer nedHexByteBuffer = ByteBuffer.wrap(nedHexBytes);
        ByteBuffer attitudeHexByteBuffer = ByteBuffer.wrap(attitudeHexBytes);

        Gps gps = new GpsDecoder().decode(gpsHexByteBuffer);
        Gyrometer gyrometer = new GyrometerDecoder().decode(gyrometerHexByteBuffer);
        Accelerometer accelerometer = new AccelerometerDecoder().decode(accelerometerHexByteBuffer);
        NedCoordinate ned = new NedDecoder().decode(nedHexByteBuffer);
        Attitude attitude = new AttitudeDecoder().decode(attitudeHexByteBuffer);
        return new UavSensingData(gps, gyrometer, accelerometer, ned, attitude);
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}