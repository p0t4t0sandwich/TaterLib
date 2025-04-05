/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_19.forge.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.EntityBridge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@ReqMappings(Mappings.SEARGE)
@ReqMCVersion(min = MinecraftVersion.V19, max = MinecraftVersion.V19_4)
@Mixin(Entity.class)
public abstract class EntityMixin implements EntityBridge {
    // @spotless:off
    @Shadow public abstract int shadow$getId();
    @Shadow public abstract void shadow$sendSystemMessage(Component message);
    @Shadow public abstract void shadow$remove(Entity.RemovalReason removalReason);
    @Shadow public abstract EntityType<?> shadow$getType();
    @Shadow public Level level;
    @Shadow public abstract BlockPos shadow$blockPosition();
    @Shadow public abstract Entity shadow$changeDimension(ServerLevel level);
    @Shadow public abstract void shadow$teleportTo(double x, double y, double z);
    @Shadow public abstract void shadow$setCustomName(@Nullable Component name);
    @Shadow public abstract UUID shadow$getUUID();
    // @spotless:on

    @Override
    public int bridge$entityId() {
        return this.shadow$getId();
    }

    @Override
    public void bridge$sendMessage(String message) {
        this.shadow$sendSystemMessage(Component.nullToEmpty(message));
    }

    @Override
    public void bridge$remove() {
        this.shadow$remove(Entity.RemovalReason.KILLED);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceLocation bridge$type() {
        return Registry.ENTITY_TYPE.getKey(this.shadow$getType());
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public ResourceLocation bridge$biome() {
        return this.level.getBiome(this.shadow$blockPosition()).unwrap().left().get().registry();
    }

    @Override
    public void bridge$changeDimension(ServerLevel level, Location location) {
        this.shadow$changeDimension(level);
        this.shadow$teleportTo(location.x(), location.y(), location.z());
    }

    @Override
    public void bridge$setCustomName(String name) {
        this.shadow$setCustomName(Component.nullToEmpty(name));
    }

    @Override
    public UUID bridge$uuid() {
        return this.shadow$getUUID();
    }
}
