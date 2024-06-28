/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15_1.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.v1_15_1.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_15_1.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.v1_15_1.forge.world.ForgeServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

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

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new StringTextComponent(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new ForgeLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        ResourceLocation biomeRegistry =
                entity.level.getBiome(entity.getCommandSenderBlockPosition()).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerWorld> serverLevel =
                    new ForgeServer(ServerLifecycleHooks.getCurrentServer())
                            .world(location.world().dimension())
                            .map(ForgeServerWorld.class::cast)
                            .map(ForgeServerWorld::world);
            if (!serverLevel.isPresent()) return;
            if (entity instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) entity)
                        .teleportTo(
                                serverLevel.get(),
                                location.x(),
                                location.y(),
                                location.z(),
                                entity.getRotationVector().y,
                                entity.getRotationVector().x);
                return;
            } else {
                entity.changeDimension(serverLevel.get().dimension.getType());
            }
        }
        entity.teleportTo(location.x(), location.y(), location.z());
    }
}
