package dev.neuralnexus.taterlib.loader.api.data.forge;

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
