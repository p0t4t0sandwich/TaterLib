package dev.neuralnexus.taterlib.utils.forge.modern;

import dev.neuralnexus.taterlib.api.info.ModInfo;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Static methods to adapt Forge classes to TaterLib abstractions.
 */
public class FMLAdapters {
    public static ModInfo adaptModContainer(IModInfo modContainer) {
        return new ModInfo(
            modContainer.getModId(),
            modContainer.getDisplayName(),
            modContainer.getVersion().toString()
        );
    }

    public static List<ModInfo> adaptModList(ModList modlist) {
        return modlist.getMods().stream()
                .map(FMLAdapters::adaptModContainer)
                .collect(Collectors.toList());
    }
}
