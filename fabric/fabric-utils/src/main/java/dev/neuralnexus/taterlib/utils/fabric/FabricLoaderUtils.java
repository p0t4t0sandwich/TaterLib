package dev.neuralnexus.taterlib.utils.fabric;

import net.fabricmc.loader.api.FabricLoader;

/** General Fabric Loader utilities. */
public class FabricLoaderUtils {
    public static String getModLoaderVersion() {
        return FabricLoader.getInstance()
                        .getModContainer("fabric-api-base")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString()
                + FabricLoader.getInstance()
                        .getModContainer("fabric-loader")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString();
    }
}
