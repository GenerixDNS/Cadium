package org.cadium.base.futures;

public interface Future<T> {

    public T join();

    public T getNow();

}
