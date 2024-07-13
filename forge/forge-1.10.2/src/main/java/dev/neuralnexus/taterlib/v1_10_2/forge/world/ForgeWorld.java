/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_10_2.forge.world;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_10_2.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_10_2.forge.player.ForgePlayer;

import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Forge implementation of {@link World}. */
public class ForgeWorld implements World {
    private final net.minecraft.world.World level;

    public ForgeWorld(net.minecraft.world.World level) {
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

    @Override
    public List<Player> players() {
        return level.playerEntities.stream().map(ForgePlayer::new).collect(Collectors.toList());
    }

    @Override
    public ResourceKey dimension() {
        return ResourceKey.of(
                level.provider.getDimensionType().getName().replace(" ", "_").toLowerCase());
    }

    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((ForgeEntity) entity).entity();
        return level
                .getEntitiesInAABBexcluding(
                        mcEntity,
                        mcEntity.getEntityBoundingBox().expand(radius, radius, radius),
                        e -> predicate.test(new ForgeEntity(e)))
                .stream()
                .map(ForgeEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return level
                .getEntitiesInAABBexcluding(
                        ((ForgeEntity) entity).entity(),
                        new AxisAlignedBB(
                                pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new ForgeEntity(e)))
                .stream()
                .map(ForgeEntity::new)
                .collect(Collectors.toList());
    }
}
