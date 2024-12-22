/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.fabricmc.loader.api;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/** Fake Fabric interface. */
public interface FabricLoader {
    static FabricLoader getInstance() {
        return new FabricLoader() {
            @Override
            public Collection<ModContainer> getAllMods() {
                return Collections.emptyList();
            }

            @Override
            public Optional<ModContainer> getModContainer(String id) {
                return Optional.empty();
            }
        };
    }

    Collection<ModContainer> getAllMods();

    Optional<ModContainer> getModContainer(String id);
}
