package dev.neuralnexus.taterlib.loader.platforms;

import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.loader.TaterLibLoader;
import dev.neuralnexus.taterlib.plugin.Loader;

import net.minecraftforge.fml.common.Mod;

/** Forge entry point. */
@Mod(
        value = TaterLib.Constants.PROJECT_ID,
        modid = TaterLib.Constants.PROJECT_ID,
        useMetadata = true,
        serverSideOnly = true,
        acceptableRemoteVersions = "*")
public class ForgeLoaderPlugin {
    private final Loader loader;

    public ForgeLoaderPlugin() {
        loader = new TaterLibLoader(this, null);
        loader.registerPlugin(new ForgeTaterLibPlugin());
        loader.onInit();
        loader.onEnable();
    }
}
