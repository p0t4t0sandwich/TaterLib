/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21.fabric.api.minecraft.world.entity;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.Identifiable;
import dev.neuralnexus.taterapi.entity.Nameable;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_21.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.v1_21.vanilla.world.VanillaServerWorld;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Interface.Remap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;
import java.util.UUID;

@ReqMappings(Mappings.YARN_INTERMEDIARY)
@ReqMCVersion(min = MinecraftVersion.V21, max = MinecraftVersion.V21_1)
@Mixin(net.minecraft.world.entity.Entity.class)
@Implements({
    @Interface(iface = CommandSender.class, prefix = "cmdSender$", remap = Remap.NONE),
    @Interface(iface = Entity.class, prefix = "entity$", remap = Remap.NONE),
    @Interface(iface = Nameable.class, prefix = "nameable$", remap = Remap.NONE),
    @Interface(iface = Identifiable.class, prefix = "identifiable$", remap = Remap.NONE)
})
public abstract class Entity_API {
    @Shadow
    public abstract void shadow$sendSystemMessage(Component message);

    @Shadow
    public abstract int shadow$getId();

    @Shadow
    public abstract void shadow$remove(RemovalReason removalReason);

    @Shadow
    public abstract EntityType<?> shadow$getType();

    @Shadow
    public abstract Level shadow$level();

    @Shadow
    public abstract BlockPos shadow$blockPosition();

    @Shadow
    public abstract void shadow$teleportTo(double x, double y, double z);

    @Shadow
    @Nullable public abstract MinecraftServer shadow$getServer();

    @Shadow
    public abstract net.minecraft.world.entity.Entity shadow$changeDimension(
            DimensionTransition dimensionTransition);

    @Shadow
    @Nullable public abstract Component shadow$getCustomName();

    @Shadow
    public abstract void shadow$setCustomName(@Nullable Component name);

    @Shadow
    public abstract UUID shadow$getUUID();

    public void cmdSender$sendMessage(String message) {
        this.shadow$sendSystemMessage(Component.nullToEmpty(message));
    }

    public int entity$entityId() {
        return this.shadow$getId();
    }

    public void entity$remove() {
        this.shadow$remove(RemovalReason.KILLED);
    }

    @SuppressWarnings("DataFlowIssue")
    public ResourceKey entity$type() {
        return (ResourceKey) (Object) BuiltInRegistries.ENTITY_TYPE.getKey(this.shadow$getType());
    }

    public Location entity$location() {
        return new VanillaLocation((net.minecraft.world.entity.Entity) (Object) this);
    }

    @SuppressWarnings({"DataFlowIssue", "OptionalGetWithoutIsPresent", "resource"})
    public ResourceKey entity$biome() {
        return (ResourceKey)
                (Object)
                        this.shadow$level()
                                .getBiome(this.shadow$blockPosition())
                                .unwrap()
                                .left()
                                .get()
                                .registry();
    }

    @SuppressWarnings({"DataFlowIssue", "resource"})
    public void entity$teleport(Location location) {
        if (location.world().dimension().equals(((Entity) this).dimension())) {
            this.shadow$teleportTo(location.x(), location.y(), location.z());
        } else {
            if (this.shadow$getServer() == null) return;
            Optional<ServerLevel> serverLevel =
                    ((Server) this.shadow$getServer())
                            .world(location.world().dimension())
                            .map(VanillaServerWorld.class::cast)
                            .map(VanillaServerWorld::unwrap);
            if (serverLevel.isEmpty()) return;
            this.shadow$changeDimension(
                    new DimensionTransition(
                            serverLevel.get(),
                            new Vec3(location.x(), location.y(), location.z()),
                            Vec3.ZERO,
                            location.yaw(),
                            location.pitch(),
                            DimensionTransition.DO_NOTHING));
        }
    }

    @SuppressWarnings("DataFlowIssue")
    public Optional<String> nameable$customName() {
        if (this.shadow$getCustomName() == null) return Optional.empty();
        return Optional.of(this.shadow$getCustomName().getString());
    }

    public void nameable$setCustomName(String name) {
        this.shadow$setCustomName(Component.nullToEmpty(name));
    }

    public UUID identifiable$uuid() {
        return this.shadow$getUUID();
    }
}
