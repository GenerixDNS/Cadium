package org.cadium.converter;

public interface ByteConverter {

    public <T> byte[] toBytes(T object);

    public Object of(byte[] bytes);

}
