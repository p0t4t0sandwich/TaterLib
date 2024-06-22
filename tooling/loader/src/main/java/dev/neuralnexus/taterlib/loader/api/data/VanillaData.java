package dev.neuralnexus.taterlib.loader.api.data;

import dev.neuralnexus.taterlib.loader.api.MinecraftVersion;
import dev.neuralnexus.taterlib.loader.api.ModInfo;
import dev.neuralnexus.taterlib.loader.api.PlatformData;
import dev.neuralnexus.taterlib.loader.utils.MixinServiceUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/** Stores information about the vanilla platform */
public class VanillaData implements PlatformData {
    @Override
    public MinecraftVersion minecraftVersion() {
        String version = "Unknown";
        try {
            version = MixinServiceUtil.mcVersion();
        } catch (ClassNotFoundException | IOException ignored) {
        }
        return MinecraftVersion.from(version);
    }

    @Override
    public String modLoaderVersion() {
        return "Vanilla";
    }

    @Override
    public List<ModInfo> modList() {
        return Collections.emptyList();
    }
}
