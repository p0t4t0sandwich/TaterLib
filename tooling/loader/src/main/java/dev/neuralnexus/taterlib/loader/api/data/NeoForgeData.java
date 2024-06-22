package dev.neuralnexus.taterlib.loader.api.data;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the NeoForge platform */
public class NeoForgeData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(FMLLoader.versionInfo().mcVersion());
    }

    @Override
    public String modLoaderVersion() {
        return FMLLoader.versionInfo().neoForgeVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return ModList.get().getMods().stream()
                .map(modContainer -> new ModInfo(
                        modContainer.getModId(),
                        modContainer.getDisplayName(),
                        modContainer.getVersion().toString()))
                .collect(Collectors.toList());
    }
}
