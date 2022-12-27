package org.cadium.converter;

public class DefaultByteConverterImpl implements ByteConverter {

    @Override
    public <T> byte[] toBytes(T object) {
        final var arrayPot = new ByteArrayPot(ByteArrayFactory.newByteArray(1024), 20);
        final var fields = object.getClass().getDeclaredFields();
        for (final var layer : ByteLayer.defaults()) {
            layer.encode(arrayPot, fields);
        }
        return arrayPot.bytes();
    }

    @Override
    public Object of(byte[] bytes) {
        return null;
    }

}
