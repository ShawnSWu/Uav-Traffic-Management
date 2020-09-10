package com.nutn.utm.utility.cayenne.decoder;

import com.nutn.utm.utility.cayenne.Decoder;
import com.nutn.utm.utility.cayenne.type.NedCoordinate;

import java.nio.ByteBuffer;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class NedDecoder implements Decoder<NedCoordinate> {

    @Override
    public NedCoordinate decode(final ByteBuffer buffer) {
        return new NedCoordinate(
                Decoder.getShort(buffer, 0.001f),
                Decoder.getShort(buffer, 0.001f),
                Decoder.getShort(buffer, 0.001f));
    }
}
