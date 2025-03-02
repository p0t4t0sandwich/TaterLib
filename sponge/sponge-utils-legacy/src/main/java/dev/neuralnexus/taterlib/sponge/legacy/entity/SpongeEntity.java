/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.entity;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.sponge.legacy.server.SpongeServer;
import dev.neuralnexus.taterlib.sponge.legacy.world.SpongeLocation;
import dev.neuralnexus.taterlib.sponge.legacy.world.SpongeWorld;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;

import java.util.Optional;
import java.util.UUID;

/** Sponge implementation of {@link Entity}. */
public class SpongeEntity implements Entity, Wrapped<org.spongepowered.api.entity.Entity> {
    private final org.spongepowered.api.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeEntity(org.spongepowered.api.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public org.spongepowered.api.entity.Entity unwrap() {
        return this.entity;
    }

    @Override
    public UUID uuid() {
        return this.entity.getUniqueId();
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
    public ResourceKey type() {
        return ResourceKey.of(
                this.entity.getType().toString().split("entity\\.")[1].replace(".", ":"));
    }

    @Override
    public Optional<String> customName() {
        if (!this.entity.get(Keys.DISPLAY_NAME).isPresent()
                && this.entity.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return Optional.empty();
        }
        return Optional.of(this.entity.get(Keys.DISPLAY_NAME).get().toString());
    }

    @Override
    public void setCustomName(String name) {
        this.entity.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    @Override
    public Location location() {
        return new SpongeLocation(this.entity.getLocation());
    }

    @Override
    public ResourceKey biome() {
        return ResourceKey.of(
                this.entity
                        .getWorld()
                        .getBiome(this.entity.getLocation().getBlockPosition())
                        .getId());
    }

    @Override
    public void teleport(Location location) {
        Optional<World> serverLevel =
                SpongeServer.instance()
                        .world(location.world().dimension())
                        .map(SpongeWorld.class::cast)
                        .map(SpongeWorld::unwrap);
        if (!serverLevel.isPresent()) return;
        this.entity.setLocation(
                new org.spongepowered.api.world.Location<>(
                        serverLevel.get(), location.x(), location.y(), location.z()));
    }

    @Override
    public boolean hasPermission(String permission) {
        return PermsAPI.instance().hasPermission(this, permission);
    }

    @Override
    public boolean hasPermission(int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permissionLevel);
    }

    @Override
    public boolean hasPermission(String permission, int permissionLevel) {
        return PermsAPI.instance().hasPermission(this, permission, permissionLevel);
    }
}
