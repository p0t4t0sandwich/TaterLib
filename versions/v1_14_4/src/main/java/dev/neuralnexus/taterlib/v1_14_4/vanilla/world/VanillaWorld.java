/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_14_4.vanilla.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.block.VanillaBlock;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.bridge.world.level.LevelBridge;

import net.minecraft.world.phys.AABB;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link Level}. */
public class VanillaWorld implements Level, Wrapped<net.minecraft.world.level.Level> {
    private final net.minecraft.world.level.@NonNull Level level;

    public VanillaWorld(final net.minecraft.world.level.@NonNull Level level) {
        this.level = level;
    }

    @Override
    public net.minecraft.world.level.@NonNull Level unwrap() {
        return this.level;
    }

    @Override
    public List<Player> players() {
        return this.level.players().stream().map(Player.class::cast).collect(Collectors.toList());
    }

    @Override
    public @NonNull Identifier dimension() { // TODO: Revise
        return Identifier.of(((LevelBridge) this.level).bridge$dimension().toString());
    }

    @Override
    public @NonNull Block block(final @NonNull BlockPos pos) {
        final net.minecraft.core.BlockPos blockPos =
                new net.minecraft.core.BlockPos(pos.blockX(), pos.blockY(), pos.blockZ());
        return new VanillaBlock(blockPos, this.level.getBlockState(blockPos).getBlock());
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        net.minecraft.world.entity.Entity mcEntity = (net.minecraft.world.entity.Entity) entity;
        return this.level
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
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        return this.level
                .getEntities(
                        (net.minecraft.world.entity.Entity) entity,
                        new AABB(pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test((Entity) e))
                .stream()
                .map(Entity.class::cast)
                .collect(Collectors.toList());
    }
}
