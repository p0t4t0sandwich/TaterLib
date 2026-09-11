/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.entity;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_8_9.sponge.SpongeFactories;
import dev.neuralnexus.taterlib.v1_8_9.sponge.server.SpongeServer;
import dev.neuralnexus.taterlib.v1_8_9.sponge.world.SpongeLocation;
import dev.neuralnexus.taterlib.v1_8_9.sponge.world.SpongeWorld;

import org.jspecify.annotations.NonNull;
import org.spongepowered.api.world.World;

import java.util.Optional;

/** Sponge implementation of {@link Entity}. */
public class SpongeEntity implements Entity, Wrapped<org.spongepowered.api.entity.Entity> {
    private final org.spongepowered.api.entity.@NonNull Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeEntity(final org.spongepowered.api.entity.@NonNull Entity entity) {
        this.entity = entity;
    }

    @Override
    public org.spongepowered.api.entity.@NonNull Entity unwrap() {
        return this.entity;
    }

    @Override
    public int entityId() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void remove() {
        this.entity.remove();
    }

    @Override
    public Identifier type() {
        return Identifier.of(
                this.entity.getType().toString().split("entity\\.")[1].replace(".", ":"));
    }

    @Override
    public @NonNull Location location() {
        return new SpongeLocation(this.entity.getLocation());
    }

    @Override
    public @NonNull Identifier biome() {
        return SpongeFactories.worldBiome.get(this.entity.getWorld(), this.location().pos());
    }

    @Override
    public void teleport(Location location) {
        Optional<World> serverLevel =
                SpongeServer.instance()
                        .world(location.level().dimension())
                        .map(SpongeWorld.class::cast)
                        .map(SpongeWorld::unwrap);
        if (!serverLevel.isPresent()) return;
        this.entity.setLocation(
                new org.spongepowered.api.world.Location<>(
                        serverLevel.get(), location.x(), location.y(), location.z()));
    }

    @Override
    public boolean hasPermission(String permission) {
        // TODO: Replace with new CrossPerms
        throw new VersionFeatureNotSupportedException();
        // return PermsAPI.instance().hasPermission(this, permission);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        // TODO: Replace with new CrossPerms
        throw new VersionFeatureNotSupportedException();
        // return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Override
    public boolean hasPermission(String permission, int permissionLevel) {
        // TODO: Replace with new CrossPerms
        throw new VersionFeatureNotSupportedException();
        // return PermsAPI.instance().hasPermission(this, permission, permissionLevel);
    }
}
