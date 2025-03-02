/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.fabric.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_8_9.fabric.entity.FabricEntity;
import dev.neuralnexus.taterlib.v1_8_9.fabric.entity.player.FabricPlayer;

import net.minecraft.util.math.Box;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Fabric implementation of {@link World}. */
public class FabricWorld implements World, Wrapped<net.minecraft.world.World> {
    private final net.minecraft.world.World level;

    public FabricWorld(net.minecraft.world.World level) {
        this.level = level;
    }

    @Override
    public net.minecraft.world.World unwrap() {
        return this.level;
    }

    @Override
    public List<Player> players() {
        return this.level.playerEntities.stream()
                .map(FabricPlayer::new)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceKey dimension() {
        return ResourceKey.of(this.level.dimension.getName().replace(" ", "_").toLowerCase());
    }

    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        net.minecraft.entity.Entity mcEntity = ((FabricEntity) entity).unwrap();
        return this.level
                .getEntitiesIn(
                        mcEntity,
                        mcEntity.getBoundingBox().expand(radius, radius, radius),
                        e -> predicate.test(new FabricEntity(e)))
                .stream()
                .map(FabricEntity::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return this.level
                .getEntitiesIn(
                        ((FabricEntity) entity).unwrap(),
                        new Box(pos1.x(), pos1.y(), pos1.z(), pos2.x(), pos2.y(), pos2.z()),
                        e -> predicate.test(new FabricEntity(e)))
                .stream()
                .map(FabricEntity::new)
                .collect(Collectors.toList());
    }
}
