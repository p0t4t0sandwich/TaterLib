/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.minecraftforge.forgespi.language;

import net.minecraftforge.forgespi.locating.IModFile;

import java.util.List;
import java.util.Map;

public interface IModFileInfo {
    List<IModInfo> getMods();

    // UnmodifiableConfig getConfig();

    String getModLoader();

    // VersionRange getModLoaderVersion();

    boolean showAsResourcePack();

    Map<String, Object> getFileProperties();

    IModFile getFile();
}
