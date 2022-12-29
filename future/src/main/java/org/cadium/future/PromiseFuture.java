package org.cadium.future;

public interface PromiseFuture<T> extends Future<T> {

    public T get();

    public T getNow();

}
