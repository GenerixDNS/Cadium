package org.cadium.base.objects;

import java.util.function.Consumer;

public interface CadiumDocumentReader {

    public CadiumDocumentReader read();

    public void foreach(Consumer<CadiumObject> action);

    public int count();

}
