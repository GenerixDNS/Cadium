package org.cadium.base.futures;

public interface Future<T> {

    public T join();

    public void run();

    public T getNow();

    public boolean hasReturn();

}
