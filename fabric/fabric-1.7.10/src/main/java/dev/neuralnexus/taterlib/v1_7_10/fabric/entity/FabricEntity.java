/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.entity;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.server.Server;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_7_10.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.v1_7_10.fabric.world.FabricServerWorld;

import net.minecraft.server.world.ServerWorld;

import java.util.Optional;
import java.util.UUID;

/** Fabric implementation of {@link Entity}. */
public class FabricEntity implements Entity, Wrapped<net.minecraft.entity.Entity> {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Fabric entity.
     */
    public FabricEntity(net.minecraft.entity.Entity entity) {
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
        return this.entity.getEntityId();
    }

    @Override
    public void remove() {
        this.entity.remove();
    }

    @Override
    public ResourceKey type() {
        return ResourceKey.of(
                this.entity.getTranslationKey().split("entity\\.")[1].replace(".", ":"));
    }

    @Override
    public Optional<String> customName() {
        if (this.entity.getName() == null) return Optional.empty();
        return Optional.of(this.entity.getName().asFormattedString());
    }

    @Override
    public void setCustomName(String name) {
        // TODO: Implement
        // NAME TAGS SUPPORT
        //        entity.setCustomName(name);
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public Location location() {
        return new FabricLocation(this.entity);
    }

    @Override
    public ResourceKey biome() {
        return ResourceKey.of(this.entity.world.getBiome((int) entity.x, (int) entity.z).name);
    }

    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerWorld> serverLevel =
                    ((Server) ((ServerWorld) this.entity.world).getServer())
                            .world(location.world().dimension())
                            .map(FabricServerWorld.class::cast)
                            .map(FabricServerWorld::unwrap);
            if (!serverLevel.isPresent()) return;
            this.entity.teleportToDimension(serverLevel.get().dimension.dimensionType);
        }
        this.entity.updatePosition(location.x(), location.y(), location.z());
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
