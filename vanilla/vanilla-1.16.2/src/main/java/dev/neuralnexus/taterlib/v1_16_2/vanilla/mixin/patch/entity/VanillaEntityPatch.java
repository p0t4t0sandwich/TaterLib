/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_2.vanilla.mixin.patch.entity;

import dev.neuralnexus.conditionalmixins.annotations.ReqMCVersion;
import dev.neuralnexus.conditionalmixins.annotations.ReqPlatform;
import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.Platform;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_16.vanilla.entity.VanillaEntity;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Patch mixin for VanillaEntity */
@ReqPlatform(not = Platform.FORGE)
@ReqMCVersion(min = MinecraftVersion.V1_16_2, max = MinecraftVersion.V1_16_5)
@Mixin(value = VanillaEntity.class, remap = false)
public class VanillaEntityPatch {
    @Final @Shadow private Entity entity;

    /**
     * @author p0t4t0sandwich
     * @reason Patch VanillaEntity.biome
     */
    @Overwrite
    public ResourceKey biome(CallbackInfo ci) {
        return (ResourceKey)
                (Object)
                        BuiltinRegistries.BIOME.getKey(
                                entity.level.getBiome(entity.blockPosition()));
    }
}
