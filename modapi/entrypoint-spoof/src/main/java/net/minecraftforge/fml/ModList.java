/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml;

import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Fake Forge modlist. */
public class ModList {
    private static final ModList INSTANCE = new ModList();

    public static ModList get() {
        return INSTANCE;
    }

    public List<ModInfo> getMods() {
        return Collections.emptyList();
    }

    private List<ModContainer> mods;

    public List<ModContainer> getLoadedMods() {
        return this.mods;
    }

    public Optional<? extends ModContainer> getModContainerById(String modId) {
        return Optional.empty();
    }

    private boolean dummyBoolean;

    public boolean isLoaded(String modTarget) {
        return dummyBoolean;
    }
}
