/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resources.Identifier;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.registry.IRegistry;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Forge implementation of {@link World}. */
public class ForgeWorld implements World, Wrapped<net.minecraft.world.World> {
    private final net.minecraft.world.World level;

    public ForgeWorld(net.minecraft.world.World level) {
        this.level = level;
    }

    @Override
    public net.minecraft.world.World unwrap() {
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
    public Identifier dimension() { // TODO: Revise
        return Identifier.of(
                IRegistry.field_212622_k.getKey(this.level.dimension.getType()).toString());
    }

    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
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
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
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
