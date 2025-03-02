/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.ServerWorld;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.player.BukkitPlayer;

import org.bukkit.util.BoundingBox;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link World}. */
public class BukkitWorld implements ServerWorld, World, Wrapped<org.bukkit.World> {
    private final org.bukkit.World world;

    /**
     * Creates a new world.
     *
     * @param world The Bukkit world.
     */
    public BukkitWorld(org.bukkit.World world) {
        this.world = world;
    }

    @Override
    public org.bukkit.World unwrap() {
        return this.world;
    }

    @Override
    public List<Player> players() {
        return this.world.getPlayers().stream().map(BukkitPlayer::new).collect(Collectors.toList());
    }

    @Override
    public ResourceKey dimension() {
        return ResourceKey.of(this.world.getName());
    }

    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return this.world
                .getNearbyEntities(
                        new org.bukkit.Location(world, pos1.x(), pos1.y(), pos1.z()),
                        pos1.distance(pos2),
                        pos1.distance(pos2),
                        pos1.distance(pos2),
                        e -> predicate.test(new BukkitEntity(e)))
                .stream()
                .map(BukkitEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        return this.world
                .getNearbyEntities(
                        BoundingBox.of(
                                ((BukkitEntity) entity).unwrap().getLocation(),
                                radius,
                                radius,
                                radius),
                        e -> predicate.test(new BukkitEntity(e)))
                .stream()
                .map(BukkitEntity::new)
                .collect(Collectors.toList());
    }
}
