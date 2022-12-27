package org.cadium.commons.futures;

import org.cadium.base.exceptions.UnknownAccessException;
import org.cadium.base.futures.CadiumFuture;
import org.cadium.base.futures.CadiumFutureFactory;
import org.cadium.base.futures.FutureValue;

public class DefaultCadiumFutureFactory implements CadiumFutureFactory {

    @Override
    public <T> CadiumFuture<T> newFuture(FutureValue<T> value) {
        try {
            return new DefaultCadiumFuture<>(value);
        } catch (UnknownAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> CadiumFuture<T> newFuture(Runnable runnable) {
        try {
            return new DefaultCadiumFuture<>(runnable);
        } catch (UnknownAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int opened() {
        return 0;
    }

}
