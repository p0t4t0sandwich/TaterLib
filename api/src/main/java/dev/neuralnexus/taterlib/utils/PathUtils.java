/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Utility class for path operations */
public class PathUtils {
    /** Get current working directory. */
    public static Path getCurrentWorkingDirectory() {
        return Paths.get("." + File.separator).toAbsolutePath().normalize();
    }

    /** Get the path to the mods folder. */
    public static Path getModsFolder() {
        return getCurrentWorkingDirectory().resolve("mods");
    }

    /** Get the path to the config folder. */
    public static Path getConfigFolder() {
        return getCurrentWorkingDirectory().resolve("config");
    }

    /** Get the path to the plugins folder. */
    public static Path getPluginsFolder() {
        return getCurrentWorkingDirectory().resolve("plugins");
    }
}
