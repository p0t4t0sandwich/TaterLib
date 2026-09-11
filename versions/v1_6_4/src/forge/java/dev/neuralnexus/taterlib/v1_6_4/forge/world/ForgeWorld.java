/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_6_4.forge.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.block.Block;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.BlockPos;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.level.Level;
import dev.neuralnexus.taterlib.v1_6_4.forge.block.ForgeBlock;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_6_4.forge.entity.player.ForgePlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import org.jspecify.annotations.NonNull;

import java.util.List;
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
    @SuppressWarnings("unchecked")
    public List<Player> players() {
        return ((List<EntityPlayer>) this.level.playerEntities)
                .stream().map(ForgePlayer::new).collect(Collectors.toList());
    }

    @Override
    public @NonNull Identifier dimension() {
        return Identifier.of(
                this.level.provider.getDimensionName().replace(" ", "_").toLowerCase());
    }

    @Override // TODO: Find biome registry
    public @NonNull Identifier biome(final @NonNull BlockPos pos) {
        return Identifier.of(
                this.level.provider.getBiomeGenForCoords(pos.blockX(), pos.blockZ()).biomeName);
    }

    @Override
    public @NonNull Block block(final @NonNull BlockPos pos) {
        return new ForgeBlock(
                new ChunkCoordinates(pos.blockX(), pos.blockY(), pos.blockZ()),
                new net.minecraft.block.Block(
                        this.level.getBlockId(pos.blockX(), pos.blockY(), pos.blockZ()),
                        this.level.getBlockMaterial(pos.blockX(), pos.blockY(), pos.blockZ())));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> entities(
            final @NonNull Entity entity,
            final double radius,
            final @NonNull Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((ForgeEntity) entity).unwrap();
        return ((List<net.minecraft.entity.Entity>)
                        this.level.getEntitiesWithinAABBExcludingEntity(
                                mcEntity,
                                mcEntity.getBoundingBox().expand(radius, radius, radius),
                                e -> predicate.test(new ForgeEntity(e))))
                .stream().map(ForgeEntity::new).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Entity> entities(
            final @NonNull Entity entity,
            final @NonNull Location pos1,
            final @NonNull Location pos2,
            final @NonNull Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((ForgeEntity) entity).unwrap();
        return ((List<net.minecraft.entity.Entity>)
                        this.level.getEntitiesWithinAABBExcludingEntity(
                                mcEntity,
                                mcEntity.boundingBox.setBounds(
                                        pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                                e -> predicate.test(new ForgeEntity(e))))
                .stream().map(ForgeEntity::new).collect(Collectors.toList());
    }
}
