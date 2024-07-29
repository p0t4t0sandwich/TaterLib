/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16.vanilla.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_16.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.v1_16.vanilla.world.VanillaServerWorld;

import net.minecraft.core.Registry;
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

    @Override
    public UUID uuid() {
        return entity.getUUID();
    }

    @Override
    public int entityId() {
        return entity.getId();
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) (Object) Registry.ENTITY_TYPE.getKey(entity.getType());
    }

    @Override
    public Optional<String> customName() {
        if (entity.getCustomName() == null) return Optional.empty();
        return Optional.of(entity.getCustomName().toString());
    }

    @Override
    public void setCustomName(String name) {
        entity.setCustomName(Component.nullToEmpty(name));
    }

    @Override
    public Location location() {
        return new VanillaLocation(entity);
    }

    @Override
    public ResourceKey biome() {
        return (ResourceKey)
                (Object) Registry.BIOME.getKey(entity.level.getBiome(entity.blockPosition()));
    }

    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerLevel> serverLevel =
                    ((Server) entity.getServer())
                            .world(location.world().dimension())
                            .map(VanillaServerWorld.class::cast)
                            .map(VanillaServerWorld::world);
            if (!serverLevel.isPresent()) return;
            if (entity instanceof ServerPlayer) {
                ((ServerPlayer) entity)
                        .teleportTo(
                                serverLevel.get(),
                                location.x(),
                                location.y(),
                                location.z(),
                                entity.getRotationVector().y,
                                entity.getRotationVector().x);
                return;
            } else {
                entity.changeDimension(serverLevel.get());
            }
        }
        entity.teleportTo(location.x(), location.y(), location.z());
    }
}
