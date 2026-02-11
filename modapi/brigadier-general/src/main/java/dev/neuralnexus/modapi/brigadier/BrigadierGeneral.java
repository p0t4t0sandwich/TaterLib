/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier;

import dev.neuralnexus.taterapi.loader.EntrypointLoader;

public class BrigadierGeneral {
    public static final String MOD_ID = "brigadier_general";

    public static final EntrypointLoader<BrigGenPlugin> LOADER =
            EntrypointLoader.builder().serviceClasses(BrigGenPlugin.class).build();
}
