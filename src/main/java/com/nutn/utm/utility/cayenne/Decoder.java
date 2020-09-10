package com.nutn.utm.utility.cayenne;

import java.nio.ByteBuffer;


/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public interface Decoder<T> {

    T decode(ByteBuffer buffer);

    static void putByte(final ByteBuffer buffer, final float value, final float factor) {
        final int v = (int) (value / factor);
        buffer.put((byte) v);
    }

    static float getByte(final ByteBuffer buffer, final float factor) {
        final byte b = buffer.get();

        final int value = b & 0xFF;

        return value * factor;
    }

    static void putShort(final ByteBuffer buffer, final float value, final float factor) {
        final int v = (int) (value / factor);
        buffer.put((byte) (v >> 8));
        buffer.put((byte) v);
    }

    static float getShort(final ByteBuffer buffer, final float factor) {
        final byte b1 = buffer.get();
        final byte b2 = buffer.get();

        final int value = b1 << 8 | b2 & 0xFF;

        return value * factor;
    }

    static void putMedium(final ByteBuffer buffer, final float value, final float factor) {
        final int v = (int) (value / factor);
        buffer.put((byte) (v >> 16));
        buffer.put((byte) (v >> 8));
        buffer.put((byte) v);
    }

    static float getMedium(final ByteBuffer buffer, final float factor) {
        final byte b1 = buffer.get();
        final byte b2 = buffer.get();
        final byte b3 = buffer.get();

        final int value = b1 << 16 | (b2 & 0xFF) << 8 | b3 & 0xFF;

        return value * factor;
    }
}