package com.nutn.utm.utility.cayenne.decoder;

import com.nutn.utm.utility.cayenne.Decoder;
import com.nutn.utm.utility.cayenne.type.Gyrometer;

import java.nio.ByteBuffer;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class GyrometerDecoder implements Decoder<Gyrometer> {

    @Override
    public Gyrometer decode(final ByteBuffer buffer) {
        return new Gyrometer(
                Decoder.getShort(buffer, 0.01f),
                Decoder.getShort(buffer, 0.01f),
                Decoder.getShort(buffer, 0.01f));
    }

}