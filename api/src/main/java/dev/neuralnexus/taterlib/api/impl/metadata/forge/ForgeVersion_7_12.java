/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.api.impl.metadata.forge;

import net.minecraftforge.common.ForgeVersion;

public class ForgeVersion_7_12 {
    public static String forgeVersion() {
        try {
            int majorVersion = ForgeVersion.class.getDeclaredField("majorVersion").getInt(null);
            int minorVersion = ForgeVersion.class.getDeclaredField("minorVersion").getInt(null);
            int revisionVersion =
                    ForgeVersion.class.getDeclaredField("revisionVersion").getInt(null);
            int buildVersion = ForgeVersion.class.getDeclaredField("buildVersion").getInt(null);
            return majorVersion + "." + minorVersion + "." + revisionVersion + "." + buildVersion;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return "Unknown";
        }
    }
}
