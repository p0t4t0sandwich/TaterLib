/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_15_2.bukkit.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterapi.world.level.ServerLevel;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.block.BukkitBlock;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_15_2.bukkit.entity.player.BukkitPlayer;

import org.bukkit.World;
import org.bukkit.util.BoundingBox;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link Level}. */
public class BukkitWorld implements ServerLevel, Level, Wrapped<World> {
    private final @NonNull World level;

    public BukkitWorld(final @NonNull World level) {
        this.level = level;
    }

    @Override
    public @NonNull World unwrap() {
        return this.level;
    }

    @Override
    public List<Player> players() {
        return this.level.getPlayers().stream().map(BukkitPlayer::new).collect(Collectors.toList());
    }

    @Override
    public @NonNull Identifier dimension() {
        return Identifier.of(this.level.getName());
    }

    @Override
    public @NonNull Block block(final @NonNull BlockPos pos) {
        return new BukkitBlock(this.level.getBlockAt(pos.blockX(), pos.blockY(), pos.blockZ()));
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        return this.level
                .getNearbyEntities(
                        new org.bukkit.Location(this.level, pos1.x(), pos1.y(), pos1.z()),
                        pos1.distance(pos2),
                        pos1.distance(pos2),
                        pos1.distance(pos2),
                        e -> predicate.test(new BukkitEntity(e)))
                .stream()
                .map(BukkitEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        return this.level
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
