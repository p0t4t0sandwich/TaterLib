/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_16_5.sponge.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterapi.world.level.ServerLevel;
import dev.neuralnexus.taterlib.v1_16_5.sponge.block.SpongeBlock;

import org.jspecify.annotations.NonNull;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.server.ServerWorld;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link Level}. */
@SuppressWarnings("rawtypes")
public class SpongeWorld implements ServerLevel, Level, Wrapped<World> {
    private final @NonNull World level;

    public SpongeWorld(final @NonNull World level) {
        this.level = level;
    }

    @Override
    public @NonNull World unwrap() {
        return this.level;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> players() {
        return ((Collection<org.spongepowered.api.entity.living.player.Player>) level.players())
                .stream().map(Player.class::cast).collect(Collectors.toList());
    }

    @Override
    public @NonNull Identifier dimension() { // TODO: Revise
        return Identifier.of(((ServerWorld) level).key().asString());
    }

    @Override
    public @NonNull Block block(final @NonNull BlockPos pos) {
        return new SpongeBlock(
                ((ServerWorld) this.level).location(pos.blockX(), pos.blockY(), pos.blockZ()));
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        return this.level.entities().stream()
                .map(Entity.class::cast)
                .filter(e -> e.location().distance(entity.location()) <= radius)
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        return this.level.entities().stream()
                .map(Entity.class::cast)
                .filter(e -> e.location().x() >= pos1.x() && e.location().x() <= pos2.x())
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
