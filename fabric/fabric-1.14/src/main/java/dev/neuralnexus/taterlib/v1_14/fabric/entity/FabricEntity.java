/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.v1_14.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_14.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.v1_14.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.v1_14.fabric.world.FabricServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

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

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new TranslatableComponent(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.getEntityWorld()
                .getBiome(entity.getBlockPos())
                .getTextComponent()
                .getFormattedText();
    }

    /** {@inheritDoc} */
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
        entity.requestTeleport(location.x(), location.y(), location.z());
    }
}
