/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.vanilla.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.EntityBridge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@ReqMappings(Mappings.MOJANG)
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V18_2)
@Mixin(Entity.class)
public abstract class EntityMixin implements EntityBridge {
    // @spotless:off
    @Shadow public abstract void shadow$remove(RemovalReason removalReason);
    @Shadow public abstract EntityType<?> shadow$getType();
    @Shadow public abstract Level shadow$getCommandSenderWorld();
    @Shadow public abstract BlockPos shadow$blockPosition();
    @Shadow public abstract Entity shadow$changeDimension(ServerLevel level);
    @Shadow public abstract void shadow$teleportTo(double x, double y, double z);
    // @spotless:on

    @Override
    public void bridge$remove() {
        this.shadow$remove(RemovalReason.KILLED);
    }

    @Override
    public ResourceLocation bridge$type() {
        return Registry.ENTITY_TYPE.getKey(this.shadow$getType());
    }

    @Override
    @SuppressWarnings("resource")
    public ResourceLocation bridge$biome() {
        return BuiltinRegistries.BIOME.getKey(
                this.shadow$getCommandSenderWorld().getBiome(this.shadow$blockPosition()));
    }

    @Override
    public void bridge$changeDimension(ServerLevel level, Location location) {
        this.shadow$changeDimension(level);
        this.shadow$teleportTo(location.x(), location.y(), location.z());
    }
}
