/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca The project is Licensed under <a
 * href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a> The API is Licensed
 * under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.api;

import static dev.neuralnexus.taterlib.utils.PathUtils.getConfigFolder;
import static dev.neuralnexus.taterlib.utils.PathUtils.getModsFolder;

import java.nio.file.Path;
import java.util.List;

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
