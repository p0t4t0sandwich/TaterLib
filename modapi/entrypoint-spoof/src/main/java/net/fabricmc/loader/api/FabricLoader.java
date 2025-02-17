/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
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

    EnvType getEnvironmentType();

    @Deprecated
    Object getGameInstance();
}
