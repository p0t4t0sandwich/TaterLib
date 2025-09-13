/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.b1_7_3.bukkit.entity;

import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.world.BukkitLocation;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.world.BukkitWorld;

import net.minecraft.server.EntityTypes;

import org.bukkit.craftbukkit.entity.CraftEntity;

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
    public ResourceKey type() {
        return ResourceKey.of(
                "minecraft", EntityTypes.b(((CraftEntity) this.entity).getHandle()).toLowerCase());
    }

    @Override
    public Optional<String> customName() {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setCustomName(String name) {
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public Location location() {
        return new BukkitLocation(this.entity.getLocation());
    }

    @Override
    public ResourceKey biome() {
        return ResourceKey.of(this.entity.getLocation().getBlock().getBiome().name());
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
