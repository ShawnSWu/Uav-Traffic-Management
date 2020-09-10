package com.nutn.utm.utility.cayenne.decoder;
import com.nutn.utm.utility.cayenne.Decoder;
import com.nutn.utm.utility.cayenne.type.Accelerometer;

import java.nio.ByteBuffer;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class AccelerometerDecoder implements Decoder<Accelerometer> {

    @Override
    public Accelerometer decode(final ByteBuffer buffer) {
        return new Accelerometer(
                Decoder.getShort(buffer, 0.001f),
                Decoder.getShort(buffer, 0.001f),
                Decoder.getShort(buffer, 0.001f));
    }
}
