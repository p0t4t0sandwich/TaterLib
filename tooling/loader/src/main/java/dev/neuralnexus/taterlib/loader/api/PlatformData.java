package dev.neuralnexus.taterlib.loader.api;

import java.nio.file.Path;
import java.util.List;

import static dev.neuralnexus.taterlib.loader.utils.PathUtils.getModsFolder;
import static dev.neuralnexus.taterlib.loader.utils.PathUtils.getConfigFolder;

public interface PlatformData {
    MinecraftVersion minecraftVersion();

    String modLoaderVersion();

    List<ModInfo> modList();

    default Path modFolder() {
        return getModsFolder();
    }

    default Path configFolder() {
        return getConfigFolder();
    }
}
