/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.loading.moddiscovery;

import net.minecraftforge.forgespi.language.IModFileInfo;

public abstract class ModFileInfo implements IModFileInfo {
    @Override
    public ModFile getFile() {
        return null;
    }
}
