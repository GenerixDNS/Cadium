package org.cadium.impl.futures;

import org.cadium.base.exceptions.UnknownAccessException;
import org.cadium.base.futures.FutureValue;
import org.cadium.base.futures.RawFutureValue;
import org.cadium.impl.security.Security;

@SuppressWarnings("unchecked")
public final class FutureExecutorImpl {

    public FutureExecutorImpl() {
    }

    private native Object join(Object future);

    private native void runAsynchronous(Object runnable);

    public <T> T join0(FutureValue<T> future) throws UnknownAccessException {
        if (!Security.isCore())
            throw new UnknownAccessException();

        return (T) join((RawFutureValue) future::yield);
    }

    public void runAsynchronous0(Runnable runnable) throws UnknownAccessException {
        if (!Security.isCore())
            throw new UnknownAccessException();

        runAsynchronous(runnable);
    }

}
