/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterlib.v1_13_2.forge.block.ForgeBlock;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.World;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Forge implementation of {@link Level}. */
public class ForgeWorld implements Level, Wrapped<World> {
    private final @NonNull World level;

    public ForgeWorld(final @NonNull World level) {
        this.level = level;
    }

    @Override
    public @NonNull World unwrap() {
        return this.level;
    }

    @Override
    public List<Player> players() {
        return this.level.playerEntities.stream()
                .map(ForgePlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NonNull Identifier dimension() { // TODO: Revise
        return Identifier.of(
                Objects.requireNonNull(
                                IRegistry.field_212622_k.getKey(this.level.dimension.getType()))
                        .toString());
    }

    @Override
    public @NonNull Block block(final @NonNull BlockPos pos) {
        final net.minecraft.util.math.BlockPos blockPos =
                new net.minecraft.util.math.BlockPos(pos.blockX(), pos.blockY(), pos.blockZ());
        return new ForgeBlock(blockPos, this.level.getBlockState(blockPos).getBlock());
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((ForgeEntity) entity).unwrap();
        return this.level
                .getEntitiesInAABBexcluding(
                        mcEntity,
                        mcEntity.getBoundingBox().grow(radius),
                        e -> predicate.test(new ForgeEntity(e)))
                .stream()
                .map(ForgeEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        return this.level
                .getEntitiesInAABBexcluding(
                        ((ForgeEntity) entity).unwrap(),
                        new AxisAlignedBB(
                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new ForgeEntity(e)))
                .stream()
                .map(ForgeEntity::new)
                .collect(Collectors.toList());
    }
}
