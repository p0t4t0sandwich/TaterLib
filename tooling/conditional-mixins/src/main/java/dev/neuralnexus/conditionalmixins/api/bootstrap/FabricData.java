package dev.neuralnexus.conditionalmixins.api.bootstrap;

import net.fabricmc.loader.api.FabricLoader;

public class FabricData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        return FabricLoader.getInstance().getModContainer("minecraft").get().getMetadata().getVersion().getFriendlyString();
    }
}
