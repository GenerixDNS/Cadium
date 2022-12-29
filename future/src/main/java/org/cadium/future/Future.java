package org.cadium.future;

public interface Future<T> {

    public boolean isComplete();

    public boolean isSuccessfully();

    public void tryCancel();

    public int getUniqueID();

}
