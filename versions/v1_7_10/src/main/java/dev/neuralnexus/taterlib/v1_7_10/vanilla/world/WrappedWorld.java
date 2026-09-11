/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.block.WrappedBlock;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world.WorldBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.world.World;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link Level}. */
public class WrappedWorld implements Level, Wrapped<World> {
    private final @NonNull World level;

    public WrappedWorld(final @NonNull World level) {
        this.level = level;
    }

    @Override
    public @NonNull World unwrap() {
        return this.level;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> players() {
        return ((List<PlayerEntity>) this.level.players)
                .stream().map(WrappedPlayer::new).collect(Collectors.toList());
    }

    @Override
    public @NonNull Identifier dimension() {
        return ((WorldBridge) this.level).bridge$dimension();
    }

    @Override
    public @NonNull Block block(final @NonNull BlockPos pos) {
        final net.minecraft.util.math.BlockPos blockPos =
                new net.minecraft.util.math.BlockPos(pos.blockX(), pos.blockY(), pos.blockZ());
        return new WrappedBlock(
                blockPos, this.level.getBlock(pos.blockX(), pos.blockY(), pos.blockZ()));
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        return ((WorldBridge) this.level).bridge$entities(entity, radius, predicate);
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        return ((WorldBridge) this.level).bridge$entities(entity, pos1, pos2, predicate);
    }
}
