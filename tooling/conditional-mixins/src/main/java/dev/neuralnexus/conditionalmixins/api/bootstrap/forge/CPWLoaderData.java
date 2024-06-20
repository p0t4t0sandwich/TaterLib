package dev.neuralnexus.conditionalmixins.api.bootstrap.forge;

import dev.neuralnexus.conditionalmixins.api.bootstrap.BootstrapData;
import cpw.mods.fml.common.Loader;

public class CPWLoaderData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        try {
            // Reflect to get cpw.mods.fml.common.Loader.MC_VERSION
            return (String) Loader.class.getField("MC_VERSION").get(null);
        } catch (ReflectiveOperationException e) {
            return "Unknown";
        }
    }
}
