package org.cadium.commons.futures;

import org.cadium.base.futures.CadiumFuture;
import org.cadium.base.futures.FutureValue;

public non-sealed class DefaultCadiumFuture<T> extends DefaultBaseFuture<T> implements CadiumFuture<T> {

    public DefaultCadiumFuture(FutureValue<T> surrendered) {
        super(surrendered);
    }

    @Override
    public boolean isDone() {
        return this.done;
    }

    @Override
    public boolean isSuccessful() {
        return this.successful;
    }

    @Override
    public boolean hasReturn() {
        return surrendered.getClass().getTypeName().equals(FutureValue.class.getTypeName());
    }

}
