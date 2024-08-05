/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15.vanilla.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;

import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link World}. */
public class VanillaWorld implements World {
    private final Level level;

    public VanillaWorld(Level level) {
        this.level = level;
    }

    /**
     * Gets the level.
     *
     * @return The level.
     */
    public Level world() {
        return level;
    }

    @Override
    public List<Player> players() {
        return level.players().stream().map(Player.class::cast).collect(Collectors.toList());
    }

    @Override
    public ResourceKey dimension() {
        return (ResourceKey) Registry.DIMENSION_TYPE.getKey(level.dimension.getType());
    }

    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.world.entity.Entity mcEntity = (net.minecraft.world.entity.Entity) entity;
        return level
                .getEntities(
                        mcEntity,
                        mcEntity.getBoundingBox().inflate(radius),
                        e -> predicate.test((Entity) e))
                .stream()
                .map(Entity.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level
                .getEntities(
                        (net.minecraft.world.entity.Entity) entity,
                        new net.minecraft.world.phys.AABB(
                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test((Entity) e))
                .stream()
                .map(Entity.class::cast)
                .collect(Collectors.toList());
    }
}
