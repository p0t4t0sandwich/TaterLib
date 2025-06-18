/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.entity;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.modapi.crossperms.PermsAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.entity.EntityBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.VanillaLocation;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.world.WrappedServerWorld;

import net.minecraft.server.world.ServerWorld;

import java.util.Optional;
import java.util.UUID;

/** Vanilla implementation of {@link Entity}. */
public class WrappedEntity implements Entity, Wrapped<net.minecraft.entity.Entity> {
    private final net.minecraft.entity.Entity entity;

    public WrappedEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public net.minecraft.entity.Entity unwrap() {
        return this.entity;
    }

    @Override
    public UUID uuid() {
        return this.entity.getUuid();
    }

    @Override
    public int entityId() {
        return this.entity.getNetworkId();
    }

    @Override
    public void remove() {
        this.entity.remove();
    }

    @Override
    public ResourceKey type() {
        // TODO: Find entity registry
        return ResourceKey.of(this.entity.getName().split("entity\\.")[1].replace(".", ":"));
    }

    @Override
    public Optional<String> customName() {
        if (this.entity.getDisplayName() == null) return Optional.empty();
        return Optional.of(this.entity.getDisplayName().getFormattedString());
    }

    @Override
    public void setCustomName(String name) {
        // TODO: Implement NAME TAGS SUPPORT
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public Location location() {
        return new VanillaLocation(this.entity);
    }

    @Override
    public ResourceKey biome() {
        // TODO: Find biome registry
        return ((EntityBridge) this.entity).bridge$biome();
    }

    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerWorld> serverLevel =
                    ((Server) ((ServerWorld) this.entity.world).getServer())
                            .world(location.world().dimension())
                            .map(WrappedServerWorld.class::cast)
                            .map(WrappedServerWorld::unwrap);
            if (!serverLevel.isPresent()) return;
            ((EntityBridge) this.entity).bridge$teleportToDimension(serverLevel.get());
        }
        this.entity.setPosition(location.x(), location.y(), location.z());
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
