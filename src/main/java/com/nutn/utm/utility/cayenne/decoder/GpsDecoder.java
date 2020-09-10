package com.nutn.utm.utility.cayenne.decoder;

import com.nutn.utm.utility.cayenne.Decoder;
import com.nutn.utm.utility.cayenne.type.Gps;

import java.nio.ByteBuffer;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class GpsDecoder implements Decoder<Gps> {

    @Override
    public Gps decode(final ByteBuffer buffer) {
        return new Gps(
                Decoder.getMedium(buffer, 0.0001f),
                Decoder.getMedium(buffer, 0.0001f),
                Decoder.getMedium(buffer, 0.01f));
    }

}