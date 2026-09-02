/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier;

import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    public FabricEntrypoint() {
        BrigadierGeneral.LOADER.load();
        BrigadierGeneral.LOADER.onInit();
    }

    @Override
    public void onInitialize() {}
}
