package org.cadium.future;

public interface FuturePromise<T> {

    public void tryGet();

    public void forceWait();

    public void forceWait(int duration, ChronoTypeTime chronoTypeTime);

    public void cancel();

    public int getCachedUniqueID();

}
