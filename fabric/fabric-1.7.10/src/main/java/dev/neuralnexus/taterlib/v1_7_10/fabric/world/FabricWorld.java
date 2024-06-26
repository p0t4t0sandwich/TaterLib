/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_7_10.fabric.world;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_7_10.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_7_10.fabric.player.FabricPlayer;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

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

    /** {@inheritDoc} */
    @Override
    @SuppressWarnings("unchecked")
    public List<Player> players() {
        return ((List<PlayerEntity>) level.playerEntities)
                .stream().map(FabricPlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return level.dimension.getName().replace(" ", "_").toLowerCase();
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
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
