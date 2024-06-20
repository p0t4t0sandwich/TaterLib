package dev.neuralnexus.conditionalmixins.api.bootstrap.forge;

import dev.neuralnexus.conditionalmixins.api.bootstrap.BootstrapData;

import net.minecraftforge.fml.common.Loader;

public class MCFLoaderData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        try {
            // Reflect to get net.minecraftforge.fml.common.Loader.MC_VERSION
            return (String) Loader.class.getField("MC_VERSION").get(null);
        } catch (ReflectiveOperationException e) {
            return "Unknown";
        }
    }
}
