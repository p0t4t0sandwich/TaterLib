/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.neoforged.fml.loading;

import net.neoforged.fml.loading.moddiscovery.ModFileInfo;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

import java.util.Collections;
import java.util.List;

/**
 * Master list of all mods <em>in the loading context. This class cannot refer outside the loading
 * package</em>
 */
public class LoadingModList {
    private static LoadingModList INSTANCE;

    public static LoadingModList get() {
        return INSTANCE;
    }

    public ModFileInfo getModFileById(String modid) {
        return new ModFileInfo();
    }

    public List<ModInfo> getMods() {
        return Collections.emptyList();
    }
}
