/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package net.minecraftforge.fml;

import net.minecraftforge.forgespi.language.IModInfo;

public class ModContainer {
    private IModInfo modInfo;
    private String modId;

    public IModInfo getModInfo() {
        return this.modInfo;
    }

    public final String getModId() {
        return this.modId;
    }
}
