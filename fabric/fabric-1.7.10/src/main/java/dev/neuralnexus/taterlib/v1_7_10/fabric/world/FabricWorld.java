/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.fabric.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_7_10.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_7_10.fabric.entity.player.FabricPlayer;

import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Fabric implementation of {@link World}. */
public class FabricWorld implements World {
    private final net.minecraft.world.World level;

    public FabricWorld(net.minecraft.world.World level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public net.minecraft.world.World world() {
        return level;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> players() {
        return ((List<PlayerEntity>) level.playerEntities)
                .stream().map(FabricPlayer::new).collect(Collectors.toList());
    }

    @Override
    public ResourceKey dimension() {
        return ResourceKey.of(level.dimension.getName().replace(" ", "_").toLowerCase());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((FabricEntity) entity).entity();
        return ((List<net.minecraft.entity.Entity>)
                        level.getEntitiesIn(
                                mcEntity,
                                mcEntity.getBox().expand(radius, radius, radius),
                                e -> predicate.test(new FabricEntity(e))))
                .stream().map(FabricEntity::new).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((FabricEntity) entity).entity();
        return ((List<net.minecraft.entity.Entity>)
                        level.getEntitiesIn(
                                mcEntity,
                                mcEntity.getBox()
                                        .set(
                                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(),
                                                pos2.z()),
                                e -> predicate.test(new FabricEntity(e))))
                .stream().map(FabricEntity::new).collect(Collectors.toList());
    }
}
