/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_8.bukkit.entity;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.world.BukkitLocation;
import dev.neuralnexus.taterlib.v1_8_8.bukkit.world.BukkitWorld;

import java.util.Optional;
import java.util.UUID;

/** Bukkit implementation of {@link Entity}. */
public class BukkitEntity implements Entity, Wrapped<org.bukkit.entity.Entity> {
    private final org.bukkit.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Bukkit entity.
     */
    public BukkitEntity(org.bukkit.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public org.bukkit.entity.Entity unwrap() {
        return this.entity;
    }

    @Override
    public UUID uuid() {
        return this.entity.getUniqueId();
    }

    @Override
    public int entityId() {
        return this.entity.getEntityId();
    }

    @Override
    public void remove() {
        this.entity.remove();
    }

    @Override
    public Identifier type() {
        return Identifier.of("minecraft", this.entity.getType().toString().toLowerCase());
    }

    @Override
    public Optional<String> customName() {
        return Optional.of(this.entity.getCustomName());
    }

    @Override
    public void setCustomName(String name) {
        this.entity.setCustomName(name);
    }

    @Override
    public Location location() {
        return new BukkitLocation(this.entity.getLocation());
    }

    @Override
    public Identifier biome() {
        return Identifier.of(this.entity.getLocation().getBlock().getBiome().name());
    }

    @Override
    public void teleport(Location location) {
        this.entity.teleport(
                new org.bukkit.Location(
                        ((BukkitWorld) location.world()).unwrap(),
                        location.x(),
                        location.y(),
                        location.z()));
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
