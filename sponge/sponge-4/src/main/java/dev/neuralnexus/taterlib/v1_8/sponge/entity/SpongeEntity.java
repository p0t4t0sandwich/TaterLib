/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_8.sponge.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_8.sponge.server.SpongeServer;
import dev.neuralnexus.taterlib.v1_8.sponge.world.SpongeLocation;
import dev.neuralnexus.taterlib.v1_8.sponge.world.SpongeServerWorld;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;

import java.util.Optional;
import java.util.UUID;

/** Sponge implementation of {@link Entity}. */
public class SpongeEntity implements Entity {
    private final org.spongepowered.api.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeEntity(org.spongepowered.api.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Sponge entity.
     *
     * @return The Sponge entity.
     */
    public org.spongepowered.api.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public ResourceKey type() {
        return ResourceKey.of(entity.getType().toString().split("entity\\.")[1].replace(".", ":"));
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> customName() {
        if (!entity.get(Keys.DISPLAY_NAME).isPresent()
                && entity.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(entity.get(Keys.DISPLAY_NAME).get().toString());
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new SpongeLocation(entity.getLocation());
    }

    /** {@inheritDoc} */
    @Override
    public ResourceKey biome() {
        return ResourceKey.of(
                entity.getWorld()
                        .getBiome(entity.getLocation().getBlockPosition().toVector2())
                        .getId());
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        Optional<World> serverLevel =
                new SpongeServer(Sponge.getServer())
                        .world(location.world().dimension())
                        .map(SpongeServerWorld.class::cast)
                        .map(SpongeServerWorld::world);
        if (!serverLevel.isPresent()) return;
        entity.setLocation(
                new org.spongepowered.api.world.Location<>(
                        serverLevel.get(), location.x(), location.y(), location.z()));
    }
}
