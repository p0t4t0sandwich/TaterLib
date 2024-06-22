package dev.neuralnexus.taterlib.loader.api.data;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import net.fabricmc.loader.api.FabricLoader;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the Fabric platform */
public class FabricData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        return MinecraftVersion.from(
                FabricLoader.getInstance()
                        .getModContainer("minecraft")
                        .get()
                        .getMetadata()
                        .getVersion()
                        .getFriendlyString());
    }

    @Override
    public String modLoaderVersion() {
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

    @Override
    public List<ModInfo> modList() {
        return FabricLoader.getInstance().getAllMods().stream()
                .map(modContainer -> new ModInfo(
                        modContainer.getMetadata().getId(),
                        modContainer.getMetadata().getName(),
                        modContainer.getMetadata().getVersion().getFriendlyString()))
                .collect(Collectors.toList());
    }
}
