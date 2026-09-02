/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.loading;

import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

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
        return null;
    }

    public List<ModInfo> getMods() {
        return Collections.emptyList();
    }
}
