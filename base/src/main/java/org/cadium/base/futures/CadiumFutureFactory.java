package org.cadium.base.futures;

public interface CadiumFutureFactory {

    public <T> CadiumFuture<T> newFuture(FutureValue<T> value);

    public <T> CadiumFuture<T> newFuture(Runnable runnable);

    public int opened();

}
