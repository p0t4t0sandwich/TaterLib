/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.fabric.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.player.Player;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_12_2.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_12_2.fabric.player.FabricPlayer;

import net.minecraft.util.math.Box;

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
    public List<Player> players() {
        return level.playerEntities.stream().map(FabricPlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return level.dimension.getDimensionType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((FabricEntity) entity).entity();
        return level
                .getEntitiesIn(
                        mcEntity,
                        mcEntity.getBoundingBox().expand(radius),
                        e -> predicate.test(new FabricEntity(e)))
                .stream()
                .map(FabricEntity::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level
                .getEntitiesIn(
                        ((FabricEntity) entity).entity(),
                        new Box(pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new FabricEntity(e)))
                .stream()
                .map(FabricEntity::new)
                .collect(Collectors.toList());
    }
}
