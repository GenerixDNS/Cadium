package org.cadium.base.futures;

public interface CadiumFuture<T> extends Future<T> {

    public boolean isDone();

    public boolean isSuccessful();

}
