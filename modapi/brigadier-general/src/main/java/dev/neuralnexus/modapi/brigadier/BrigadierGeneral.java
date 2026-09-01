/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier;

import dev.neuralnexus.taterapi.loader.EntrypointLoader;

public class BrigadierGeneral {
    public static final String MOD_ID = "brigadier_general";

    public static final EntrypointLoader<BrigGenPlugin> LOADER =
            EntrypointLoader.builder().serviceClasses(BrigGenPlugin.class).build();
}
