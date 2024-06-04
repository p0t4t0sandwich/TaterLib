package dev.neuralnexus.taterlib.utils.fabric;

import dev.neuralnexus.taterlib.api.info.ModInfo;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.util.List;
import java.util.stream.Collectors;

/** Static methods to adapt Fabric classes to TaterLib abstractions. */
public class FabricLoaderAdapters {
    public static ModInfo adaptModContainer(ModContainer modContainer) {
        return new ModInfo(
                modContainer.getMetadata().getId(),
                modContainer.getMetadata().getName(),
                modContainer.getMetadata().getVersion().getFriendlyString());
    }

    public static List<ModInfo> adaptModList() {
        return FabricLoader.getInstance().getAllMods().stream()
                .map(FabricLoaderAdapters::adaptModContainer)
                .collect(Collectors.toList());
    }
}
