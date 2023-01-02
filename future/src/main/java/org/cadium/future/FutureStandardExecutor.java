package org.cadium.future;

public final class FutureStandardExecutor {

    public static native <T> void async(int uniqueID, FutureTask<T> task);

    public static native Object collect(int uniqueID);

    public static native void flush(int uniqueID);

    public static native void wait(int uniqueID);

}
