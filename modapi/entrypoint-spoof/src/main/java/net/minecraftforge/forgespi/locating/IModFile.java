/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.forgespi.locating;

import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface IModFile {
    // IModLanguageProvider getLoader();

    Path findResource(String className);

    Supplier<Map<String, Object>> getSubstitutionMap();

    Type getType();

    Path getFilePath();

    List<IModInfo> getModInfos();

    // ModFileScanData getScanResult();

    String getFileName();

    // IModLocator getLocator();

    IModFileInfo getModFileInfo();

    enum Type {
        MOD,
        LIBRARY,
        LANGPROVIDER,
        GAMELIBRARY
    }
}
