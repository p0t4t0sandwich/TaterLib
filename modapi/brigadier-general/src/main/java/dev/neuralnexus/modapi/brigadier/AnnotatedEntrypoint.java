/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier;

// @com.velocitypowered.api.plugin.Plugin(id = Common.MOD_ID)
@net.minecraftforge.fml.common.Mod(BrigadierGeneral.MOD_ID)
@net.neoforged.fml.common.Mod(BrigadierGeneral.MOD_ID)
@org.spongepowered.api.plugin.Plugin(id = BrigadierGeneral.MOD_ID)
public class AnnotatedEntrypoint {
    public AnnotatedEntrypoint() {
        BrigadierGeneral.LOADER.load();
        BrigadierGeneral.LOADER.onInit();
    }
}
