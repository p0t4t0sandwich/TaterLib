/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_17_1.forge.core.world.entity;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.entity.EntityBridge;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
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
@ReqMCVersion(min = MinecraftVersion.V17, max = MinecraftVersion.V18_2)
@Mixin(Entity.class)
public abstract class EntityMixin implements EntityBridge {
    @Shadow
    public abstract void shadow$sendMessage(Component message, UUID uuid);

    @Shadow
    public abstract void shadow$remove(Entity.RemovalReason removalReason);

    @Shadow public abstract EntityType<?> shadow$getType();

    @Shadow
    public abstract Level shadow$getCommandSenderWorld();

    @Shadow
    public abstract BlockPos shadow$blockPosition();

    @Shadow
    public abstract Entity shadow$changeDimension(ServerLevel level);

    @Shadow
    public abstract void shadow$setCustomName(@Nullable Component name);

    @Override
    public void bridge$sendMessage(String message) {
        this.shadow$sendMessage(Component.nullToEmpty(message), Util.NIL_UUID);
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
    @SuppressWarnings({"deprecation", "resource"})
    public ResourceLocation bridge$biome() {
        return BuiltinRegistries.BIOME.getKey(
                this.shadow$getCommandSenderWorld().getBiome(this.shadow$blockPosition()));
    }

    @Override
    public void bridge$changeDimension(ServerLevel level) {
        this.shadow$changeDimension(level);
    }

    @Override
    public void bridge$setCustomName(String name) {
        this.shadow$setCustomName(Component.nullToEmpty(name));
    }
}
