/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.loading;

import net.minecraftforge.api.distmarker.Dist;

/** Fake NeoForge class */
public class FMLLoader {
    private String mcVersion;
    private String forgeVersion;
    private static Dist dist;

    public static Dist getDist() {
        return dist;
    }

    public static VersionInfo versionInfo() {
        return new VersionInfo();
    }
}
