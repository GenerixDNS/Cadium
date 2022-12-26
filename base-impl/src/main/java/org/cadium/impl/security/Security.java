package org.cadium.impl.security;

public final class Security {

    public static Class<?> getCallerClass() {
        final var trace = Thread.currentThread().getStackTrace();
        final var name = trace[trace.length-1].getClassName();
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isCore() {
        final var trace = Thread.currentThread().getStackTrace();
        int i = 0;
        for (StackTraceElement traceElement : trace) {
            if (traceElement.getClassName().startsWith("org.cadium."))
                i++;
        }
        return i > 2;
    }

}
