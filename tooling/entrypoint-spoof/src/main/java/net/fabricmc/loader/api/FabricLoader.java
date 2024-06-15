package net.fabricmc.loader.api;

import java.util.Collection;
import java.util.Collections;

/** Fake Fabric interface. */
public interface FabricLoader {
    static FabricLoader getInstance() {
        return Collections::emptySet;
    }

    Collection<ModContainer> getAllMods();
}
