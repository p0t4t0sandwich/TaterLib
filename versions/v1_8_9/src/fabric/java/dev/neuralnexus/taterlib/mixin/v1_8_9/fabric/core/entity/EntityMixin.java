/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_8_9.fabric.core.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.EntityBridge;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.LEGACY_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V8, max = MinecraftVersion.V8_9)
@Mixin(Entity.class)
public abstract class EntityMixin implements EntityBridge {
    @Shadow public World world;

    @Shadow
    public abstract BlockPos shadow$getSourceBlockPos();

    @Shadow
    public abstract void shadow$teleportToDimension(int par1);

    @Override
    public ResourceKey bridge$biome() {
        return ResourceKey.of(this.world.getBiome(this.shadow$getSourceBlockPos()).name);
    }

    @Override
    public void bridge$teleportToDimension(ServerWorld world) {
        this.shadow$teleportToDimension(world.dimension.getId());
    }
}
