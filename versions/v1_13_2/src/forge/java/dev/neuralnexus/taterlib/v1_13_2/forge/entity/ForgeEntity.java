/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.entity;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.perms.PermsAPI;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_13_2.forge.resource.ForgeResourceKey;
import dev.neuralnexus.taterlib.v1_13_2.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_13_2.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.v1_13_2.forge.world.ForgeServerWorld;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.Optional;
import java.util.UUID;

/** Forge implementation of {@link Entity}. */
public class ForgeEntity implements Entity, Wrapped<net.minecraft.entity.Entity> {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    @Override
    public net.minecraft.entity.Entity unwrap() {
        return this.entity;
    }

    @Override
    public UUID uuid() {
        return this.entity.getUniqueID();
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
    @SuppressWarnings("deprecation")
    public ResourceKey type() {
        return new ForgeResourceKey(IRegistry.field_212629_r.getKey(this.entity.getType()));
    }

    @Override
    public Optional<String> customName() {
        if (this.entity.getCustomName() == null) return Optional.empty();
        return Optional.of(this.entity.getCustomName().getString());
    }

    @Override
    public void setCustomName(String name) {
        this.entity.setCustomName(new TextComponentString(name));
    }

    @Override
    public Location location() {
        return new ForgeLocation(this.entity);
    }

    @Override
    @SuppressWarnings("deprecation")
    public ResourceKey biome() {
        return new ForgeResourceKey(
                IRegistry.field_212624_m.getKey(
                        this.entity.world.getBiome(this.entity.getPosition())));
    }

    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<WorldServer> serverLevel =
                    new ForgeServer(ServerLifecycleHooks.getCurrentServer())
                            .world(location.world().dimension())
                            .map(ForgeServerWorld.class::cast)
                            .map(ForgeServerWorld::unwrap);
            if (!serverLevel.isPresent()) return;
            if (this.entity instanceof EntityPlayerMP) {
                ((EntityPlayerMP) this.entity)
                        .teleport(
                                serverLevel.get(),
                                location.x(),
                                location.y(),
                                location.z(),
                                this.entity.rotationYaw,
                                this.entity.rotationPitch);
                return;
            } else {
                this.entity.changeDimension(
                        serverLevel.get().dimension.getType(), new Teleporter(serverLevel.get()));
            }
        }
        ((EntityLiving) this.entity).attemptTeleport(location.x(), location.y(), location.z());
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
