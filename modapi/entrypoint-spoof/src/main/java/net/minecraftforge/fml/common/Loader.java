/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.common;

import net.minecraftforge.common.ForgeVersion;

import java.util.Collections;
import java.util.List;

/** Fake Forge class. */
public class Loader {
    public static final String MC_VERSION = ForgeVersion.mcVersion;

    private static Loader instance;

    public static Loader instance() {
        if (instance == null) {
            instance = new Loader();
        }
        return instance;
    }

    public static boolean isModLoaded(String modname) {
        return instance().getModList().stream()
                .anyMatch(mod -> mod.getModId().equalsIgnoreCase(modname));
    }

    public List<ModContainer> getModList() {
        return Collections.emptyList();
    }
}
