package dev.neuralnexus.taterlib.loader.api.data.forge;

import cpw.mods.fml.common.Loader;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the CPW Loader platform */
public class CPWLoaderData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            // Reflect to get cpw.mods.fml.common.Loader.MC_VERSION
            version = (String) Loader.class.getField("MC_VERSION").get(null);
        } catch (ReflectiveOperationException ignored) {
        }
        return MinecraftVersion.from(version);
    }

    @Override
    public String modLoaderVersion() {
        return ForgeVersion_7_12.forgeVersion();
    }

    @Override
    public List<ModInfo> modList() {
        return Loader.instance().getModList().stream()
                .map(
                        modContainer ->
                                new ModInfo(
                                        modContainer.getModId(),
                                        modContainer.getName(),
                                        modContainer.getVersion()))
                .collect(Collectors.toList());
    }
}
