/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.sponge.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_17.sponge.server.SpongeServer;
import dev.neuralnexus.taterlib.v1_17.sponge.world.SpongeLocation;
import dev.neuralnexus.taterlib.v1_17.sponge.world.SpongeServerWorld;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.registry.Registry;
import org.spongepowered.api.registry.RegistryTypes;
import org.spongepowered.api.world.biome.Biome;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3d;

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

    @Override
    public UUID uuid() {
        return entity.uniqueId();
    }

    @Override
    public int entityId() {
        return -1;
    }

    @Override
    public void remove() {
        entity.remove();
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) (Object) EntityTypes.registry().valueKey(entity.type());
    }

    @Override
    public Optional<String> customName() {
        if (!entity.get(Keys.CUSTOM_NAME).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(entity.get(Keys.CUSTOM_NAME).get().toString());
    }

    @Override
    public void setCustomName(String name) {
        entity.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    @Override
    public Location location() {
        return new SpongeLocation(entity.serverLocation());
    }

    @Override
    public ResourceKey biome() {
        Biome biome = entity.location().world().biome(entity.location().blockPosition());
        Registry<Biome> registry = entity.location().world().registry(RegistryTypes.BIOME);
        return (ResourceKey) (Object) registry.findValueKey(biome).get();
    }

    @Override
    public void teleport(Location location) {
        Optional<ServerWorld> serverLevel =
                new SpongeServer(Sponge.server())
                        .world(location.world().dimension())
                        .map(SpongeServerWorld.class::cast)
                        .map(SpongeServerWorld::world);
        if (!serverLevel.isPresent()) return;
        entity.setLocation(
                ServerLocation.of(
                        serverLevel.get(), new Vector3d(location.x(), location.y(), location.z())));
    }
}
