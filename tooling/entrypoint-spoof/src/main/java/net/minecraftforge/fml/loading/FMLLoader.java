package net.minecraftforge.fml.loading;

/** Fake NeoForge class */
public class FMLLoader {
    private String mcVersion;
    private String forgeVersion;

    public static VersionInfo versionInfo() {
        return new VersionInfo();
    }
}
