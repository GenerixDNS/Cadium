package org.cadium.commons.futures;

import org.cadium.base.exceptions.UnknownAccessException;
import org.cadium.base.futures.Future;
import org.cadium.base.futures.FutureValue;
import org.cadium.impl.futures.FutureExecutorImpl;

public abstract sealed class DefaultBaseFuture<T> implements Future<T> permits DefaultCadiumFuture {

    protected static final FutureExecutorImpl futureExecutor = new FutureExecutorImpl();

    protected final Object surrendered;
    protected boolean done = false;
    protected boolean successful = false;

    public DefaultBaseFuture(FutureValue<T> surrendered) {
        this.surrendered = surrendered;
    }

    @Override
    public T join() {
        if (surrendered.getClass().getTypeName().equals(Runnable.class.getTypeName()))
            throw new UnsupportedOperationException("This operation cannot be performed with a Value which doesn't has a return!");

        try {
            return futureExecutor.join0(this);
        } catch (UnknownAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getNow() {
        if (surrendered.getClass().getTypeName().equals(Runnable.class.getTypeName())) {
            ((Runnable) this.surrendered).run();
            return null;
        }
        return ((FutureValue<T>) this.surrendered).yield();
    }

    @Override
    public void run() {
        if (!surrendered.getClass().getTypeName().equals(Runnable.class.getTypeName())) {
            throw new UnsupportedOperationException("This operation cannot be performed with a Value which does has a return!");
        }

        try {
            futureExecutor.runAsynchronous0((Runnable) this.surrendered);
        } catch (UnknownAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
