package dev.neuralnexus.conditionalmixins.api.bootstrap;

import net.neoforged.fml.loading.FMLLoader;

public class NeoForgeData implements BootstrapData {
    @Override
    public String MinecraftVersion() {
        return FMLLoader.versionInfo().mcVersion();
    }
}
