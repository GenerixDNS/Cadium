package org.cadium.base.objects;

import java.util.Collection;

public interface CadiumObjectStorage {

    public Collection<CadiumObject> getLoadedCadiumObjects();

    public void loadCadiumObject(CadiumObject cadiumObject);

    public void clearObjects();

    public void unloadObject(String name);

}
