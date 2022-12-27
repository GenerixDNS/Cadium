package org.cadium.commons.objects;

import org.cadium.base.objects.CadiumDocumentReader;
import org.cadium.base.objects.CadiumObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class DefaultCadiumDocumentReader implements CadiumDocumentReader {

    private static String REGISTRY_NAME = "registry";

    private CadiumObject[] cache;
    private final File file = new File(
            Objects.requireNonNull(DefaultCadiumDocumentReader
                    .class
                    .getClassLoader()
                    .getResource(REGISTRY_NAME + ".cad")
            ).getFile());

    @Override
    public CadiumDocumentReader read() {
        final List<String> components;
        try {
            var output = Files.readString(this.file.toPath(), Charset.defaultCharset());
            output = output.trim();
            components = new ArrayList<>(Arrays.asList(output.split("-")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.cache = new CadiumObject[components.size()];
        for (int i = 0; i < components.size(); i++) {
            final var temp = components.get(i).split(" ");
            if (temp.length == 2) {
                if (temp[1].equals("in")) {
                    this.cache[i] = new SimpleCadiumObject(temp[0], temp[1]);
                }
            }
        }
        return this;
    }

    @Override
    public void foreach(Consumer<CadiumObject> action) {
        for (final var object : this.cache) {
            action.accept(object);
        }
    }

    @Override
    public int count() {
        return this.cache.length;
    }

    public static void setRegistryName(String registryName) {
        REGISTRY_NAME = registryName;
    }

}
