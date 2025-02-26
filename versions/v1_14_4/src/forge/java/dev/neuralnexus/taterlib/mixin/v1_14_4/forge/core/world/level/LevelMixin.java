/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.forge.core.world.level;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.level.LevelBridge;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.Dimension;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.LEGACY_SEARGE)
@ReqMCVersion(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2)
@Mixin(Level.class)
public class LevelMixin implements LevelBridge {
    @Shadow @Final public Dimension dimension;

    @Override
    @SuppressWarnings("deprecation")
    public ResourceLocation bridge$dimension() {
        return Registry.DIMENSION_TYPE.getKey(this.dimension.getType());
    }
}
