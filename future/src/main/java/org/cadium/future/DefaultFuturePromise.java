package org.cadium.future;

public class DefaultFuturePromise<T> implements FuturePromise<T> {

    protected final int uniqueID = FutureFactory.next();

    @Override
    public void tryGet() {

    }

    @Override
    public void forceWait() {

    }

    @Override
    public void forceWait(int duration, ChronoTypeTime chronoTypeTime) {

    }

    @Override
    public void cancel() {

    }

    @Override
    public int getCachedUniqueID() {
        return this.uniqueID;
    }

}
