/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_16_2.vanilla.api.minecraft.world.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.core.BlockPos;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V16_2, max = MinecraftVersion.V16_5)
@Mixin(net.minecraft.world.entity.Entity.class)
@Implements(@Interface(iface = Entity.class, prefix = "entity$", remap = Remap.NONE))
@SuppressWarnings({"unused", "UnusedMixin"})
public abstract class Entity_API_biome {
    @Shadow
    public abstract Level shadow$getCommandSenderWorld();

    @Shadow
    public abstract BlockPos shadow$blockPosition();

    @SuppressWarnings("resource")
    public ResourceKey entity$biome() {
        return (ResourceKey)
                BuiltinRegistries.BIOME.getKey(
                        this.shadow$getCommandSenderWorld().getBiome(this.shadow$blockPosition()));
    }
}
