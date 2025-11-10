/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.fml;

import net.neoforged.fml.loading.moddiscovery.ModInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Fake NeoForge modlist. */
public class ModList {
    private static final ModList INSTANCE = new ModList();

    public static ModList get() {
        return INSTANCE;
    }

    public List<ModInfo> getMods() {
        return Collections.emptyList();
    }

    public List<ModContainer> getSortedMods() {
        return Collections.emptyList();
    }

    public Optional<? extends ModContainer> getModContainerById(String modId) {
        return Optional.empty();
    }

    private boolean dummyBoolean;
    public boolean isLoaded(String modTarget) {
        return dummyBoolean;
    }
}
