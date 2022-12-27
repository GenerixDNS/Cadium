package org.cadium.converter;

import java.lang.reflect.Field;

public interface ByteLayer {

    public static ByteLayer[] defaults() {
        return new ByteLayer[0];
    }

    public abstract byte[] encode(ByteArrayPot pot, Field[] fields);

}
