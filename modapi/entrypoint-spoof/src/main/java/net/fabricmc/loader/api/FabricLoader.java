/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.fabricmc.loader.api;

import net.fabricmc.api.EnvType;

import java.util.Collection;
import java.util.Optional;

/** Fake Fabric interface. */
public interface FabricLoader {
    FabricLoader fabricLoader = getInstance();

    static FabricLoader getInstance() {
        return fabricLoader;
    }

    Collection<ModContainer> getAllMods();

    Optional<ModContainer> getModContainer(String id);

    boolean isModLoaded(String id);

    EnvType getEnvironmentType();

    @Deprecated
    Object getGameInstance();
}
