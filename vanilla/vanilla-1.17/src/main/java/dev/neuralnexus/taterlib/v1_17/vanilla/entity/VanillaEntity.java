/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.vanilla.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.v1_17.vanilla.server.VanillaServer;
import dev.neuralnexus.taterlib.v1_17.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.v1_17.vanilla.world.VanillaServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;
import java.util.UUID;

/** Vanilla implementation of {@link Entity}. */
public class VanillaEntity implements Entity {
    private final net.minecraft.world.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The entity.
     */
    public VanillaEntity(net.minecraft.world.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the entity.
     *
     * @return The entity.
     */
    public net.minecraft.world.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
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
        entity.setCustomName(Component.nullToEmpty(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new VanillaLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.level.getBiome(entity.blockPosition()).toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerLevel> serverLevel =
                    VanillaServer.instance()
                            .world(location.world().dimension())
                            .map(VanillaServerWorld.class::cast)
                            .map(VanillaServerWorld::world);
            if (serverLevel.isEmpty()) return;
            if (entity instanceof ServerPlayer player) {
                player.teleportTo(
                        serverLevel.get(),
                        location.x(),
                        location.y(),
                        location.z(),
                        player.getYRot(),
                        player.getXRot());
                return;
            } else {
                entity.changeDimension(serverLevel.get());
            }
        }
        entity.teleportTo(location.x(), location.y(), location.z());
    }
}
