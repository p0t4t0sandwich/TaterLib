/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15.fabric.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_15.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_15.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.v1_15.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.v1_15.fabric.world.FabricServerWorld;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.registry.Registry;

import java.util.Optional;
import java.util.UUID;

/** Fabric implementation of {@link Entity}. */
public class FabricEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Fabric entity.
     */
    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Fabric entity.
     *
     * @return The Fabric entity.
     */
    public net.minecraft.entity.Entity entity() {
        return entity;
    }

    @Override
    public UUID uuid() {
        return entity.getUuid();
    }

    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) (Object) Registry.ENTITY_TYPE.getId(entity.getType());
    }

    @Override
    public Optional<String> customName() {
        if (entity.getCustomName() == null) return Optional.empty();
        return Optional.of(entity.getCustomName().toString());
    }

    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new LiteralText(name));
    }

    @Override
    public Location location() {
        return new FabricLocation(entity);
    }

    @Override
    public ResourceKey biome() {
        return (ResourceKey)
                (Object) Registry.BIOME.getId(entity.world.getBiome(entity.getBlockPos()));
    }

    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerWorld> serverLevel =
                    new FabricServer(FabricTaterLibPlugin.minecraftServer)
                            .world(location.world().dimension())
                            .map(FabricServerWorld.class::cast)
                            .map(FabricServerWorld::world);
            if (!serverLevel.isPresent()) return;
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                player.teleport(
                        serverLevel.get(),
                        location.x(),
                        location.y(),
                        location.z(),
                        player.yaw,
                        player.pitch);
                return;
            } else {
                entity.changeDimension(serverLevel.get().dimension.getType());
            }
        }
        entity.teleport(location.x(), location.y(), location.z());
    }
}
