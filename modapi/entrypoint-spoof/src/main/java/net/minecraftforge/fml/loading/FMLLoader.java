/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package net.minecraftforge.fml.loading;

/** Fake NeoForge class */
public class FMLLoader {
    private String mcVersion;
    private String forgeVersion;

    public static VersionInfo versionInfo() {
        return new VersionInfo();
    }
}
