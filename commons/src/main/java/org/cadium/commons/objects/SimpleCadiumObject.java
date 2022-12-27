package org.cadium.commons.objects;

import org.cadium.base.objects.CadiumObject;

public record SimpleCadiumObject(String name, String location) implements CadiumObject {
}
