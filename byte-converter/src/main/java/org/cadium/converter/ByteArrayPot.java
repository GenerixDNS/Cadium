package org.cadium.converter;

import java.nio.ByteBuffer;

public class ByteArrayPot {

    protected byte[] content;
    protected int capacity = 0;
    protected final int steps;

    public ByteArrayPot(byte[] content, int steps) {
        this.content = content;
        this.steps = steps;
    }

    @SuppressWarnings("ManualArrayCopy")
    public void writeByte(byte b) {
        if (this.content.length <= this.capacity) {
            final var temp = ByteArrayFactory.newByteArray(this.content.length + this.steps);
            for (int i = 0; i < this.content.length; i++) {
                temp[i] = this.content[i];
            }
            temp[this.capacity] = b;
            this.capacity++;
            this.content = temp;
        } else {
            this.content[this.capacity] = b;
            this.capacity++;
        }
    }

    public void foreachByte(ByteProcessor processor) {
        for (int i = 0; i < this.capacity; i++) {
            processor.handle(i, this.content[i]);
        }
    }

    public void writeBytes(byte[] bytes) {
        for (byte b : bytes) {
            this.writeByte(b);
        }
    }

    public void writeShort(short c) {
        final var buffer = ByteBuffer.allocate(2);
        buffer.putShort(c);
        this.writeBytes(buffer.array());
    }

    public void writeInt(int c) {
        final var buffer = ByteBuffer.allocate(2);
        buffer.putInt(c);
        this.writeBytes(buffer.array());
    }

    public void writeLong(long c) {
        final var buffer = ByteBuffer.allocate(2);
        buffer.putLong(c);
        this.writeBytes(buffer.array());
    }

    public void writeFloat(float c) {
        final var buffer = ByteBuffer.allocate(2);
        buffer.putFloat(c);
        this.writeBytes(buffer.array());
    }

    public void writeDouble(double c) {
        final var buffer = ByteBuffer.allocate(2);
        buffer.putDouble(c);
        this.writeBytes(buffer.array());
    }

    public void writeChar(char c) {
        final var buffer = ByteBuffer.allocate(2);
        buffer.putChar(c);
        this.writeBytes(buffer.array());
    }

    public void writeBoolean(boolean c) {
        this.writeByte((byte) (c ? 1 : 0));
    }

    public int size() {
        return this.capacity;
    }

    public byte[] bytes() {
        return this.content;
    }

}
