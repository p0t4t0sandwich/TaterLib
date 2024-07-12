/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.forge.entity;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterlib.v1_12_2.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_12_2.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_12_2.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.v1_12_2.forge.world.ForgeServerWorld;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Optional;
import java.util.UUID;

/** Forge implementation of {@link Entity}. */
public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Forge entity.
     *
     * @return The Forge entity.
     */
    public net.minecraft.entity.Entity entity() {
        return entity;
    }

    @Override
    public UUID uuid() {
        return entity.getUniqueID();
    }

    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    @Override
    public void remove() {
        entity.setDead();
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey)
                (Object)
                        GameRegistry.findRegistry(EntityEntry.class)
                                .getKey(
                                        EntityEntryBuilder.create()
                                                .entity(entity.getClass())
                                                .build());
    }

    @Override
    public Optional<String> customName() {
        if (!entity.hasCustomName()) return Optional.empty();
        return Optional.of(entity.getCustomNameTag());
    }

    @Override
    public void setCustomName(String name) {
        entity.setCustomNameTag(name);
    }

    @Override
    public Location location() {
        return new ForgeLocation(entity);
    }

    @Override
    public ResourceKey biome() {
        return (ResourceKey)
                (Object)
                        GameRegistry.findRegistry(Biome.class)
                                .getKey(entity.world.getBiome(entity.getPosition()));
    }

    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<WorldServer> serverLevel =
                    new ForgeServer(ForgeTaterLibPlugin.minecraftServer)
                            .world(location.world().dimension())
                            .map(ForgeServerWorld.class::cast)
                            .map(ForgeServerWorld::world);
            if (!serverLevel.isPresent()) return;
            entity.changeDimension(
                    serverLevel.get().getWorldType().getId(), new Teleporter(serverLevel.get()));
        }
        ((EntityLiving) entity).attemptTeleport(location.x(), location.y(), location.z());
    }
}
