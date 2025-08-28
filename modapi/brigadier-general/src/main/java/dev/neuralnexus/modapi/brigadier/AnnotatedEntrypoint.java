/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.modapi.brigadier;

// @com.velocitypowered.api.plugin.Plugin(id = Common.MOD_ID)
@net.minecraftforge.fml.common.Mod(BrigadierGeneral.MOD_ID)
@net.neoforged.fml.common.Mod(BrigadierGeneral.MOD_ID)
@org.spongepowered.api.plugin.Plugin(id = BrigadierGeneral.MOD_ID)
public class AnnotatedEntrypoint {
    public AnnotatedEntrypoint() {
        BrigadierGeneral.LOADER.load();
        BrigadierGeneral.LOADER.init();
    }
}
