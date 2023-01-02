package org.cadium.future;

public final class FutureFactory {

    private static int UNIQUE_ID_COUNTER = 0;

    public static int next() {
        int result = UNIQUE_ID_COUNTER + 1;
        UNIQUE_ID_COUNTER++;
        return result;
    }

}
