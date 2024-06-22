package dev.neuralnexus.taterlib.loader.api.data.forge;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/** Stores data about the FMLLoader platform */
public class FMLLoaderData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            try {
                // Reflect to get FMLLoader.versionInfo().mcVersion()
                Object versionInfoObject = FMLLoader.class.getMethod("versionInfo").invoke(null);
                version =
                        (String)
                                versionInfoObject
                                        .getClass()
                                        .getMethod("mcVersion")
                                        .invoke(versionInfoObject);
            } catch (ReflectiveOperationException e) {
                // Reflect to get private FMLLoader.mcVersion
                Field mcVersionField = FMLLoader.class.getDeclaredField("mcVersion");
                mcVersionField.setAccessible(true);
                version = (String) mcVersionField.get(null);
            }
        } catch (ReflectiveOperationException ignored) {
        }
        return MinecraftVersion.from(version);
    }

    @Override
    public String modLoaderVersion() {
        MinecraftVersion version = minecraftVersion();
        if (version.isOlderThan(MinecraftVersion.V1_17)) {
            return ForgeVersion_13_16.forgeVersion();
        }
        return ForgeVersion_17_21.forgeVersion();
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
