/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package net.minecraftforge.fml.common;

import java.io.File;

/** Fake Forge class. */
public interface ModContainer {
    String getModId();

    String getName();

    String getVersion();

    File getSource();
}
