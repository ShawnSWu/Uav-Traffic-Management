package com.nutn.utm.utility.cayenne.decoder;

import com.nutn.utm.utility.cayenne.Decoder;
import com.nutn.utm.utility.cayenne.type.Attitude;

import java.nio.ByteBuffer;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class AttitudeDecoder implements Decoder<Attitude> {

    @Override
    public Attitude decode(final ByteBuffer buffer) {
        return new Attitude(
                Decoder.getShort(buffer, 0.001f),
                Decoder.getShort(buffer, 0.001f),
                Decoder.getShort(buffer, 0.001f));
    }
}
