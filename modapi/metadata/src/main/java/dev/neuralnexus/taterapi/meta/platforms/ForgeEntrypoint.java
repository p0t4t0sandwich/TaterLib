package dev.neuralnexus.taterapi.meta.platforms;

import net.minecraftforge.fml.common.Mod;

@Mod(
        value = "tater_metadata",
        modid = "tater_metadata",
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgeEntrypoint {
    public ForgeEntrypoint() {
        TaterMetadata.initForge();
    }
}
