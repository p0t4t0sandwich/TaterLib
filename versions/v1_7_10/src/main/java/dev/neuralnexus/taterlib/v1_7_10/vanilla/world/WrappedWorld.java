/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.world;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterapi.world.Location;
import dev.neuralnexus.taterapi.world.World;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.bridge.world.WorldBridge;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.entity.living.player.PlayerEntity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Vanilla implementation of {@link World}. */
public class WrappedWorld implements World, Wrapped<net.minecraft.world.World> {
    private final net.minecraft.world.World level;

    public WrappedWorld(net.minecraft.world.World level) {
        this.level = level;
    }

    @Override
    public net.minecraft.world.World unwrap() {
        return this.level;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> players() {
        return ((List<PlayerEntity>) this.level.players)
                .stream().map(WrappedPlayer::new).collect(Collectors.toList());
    }

    @Override
    public ResourceKey dimension() {
        return ((WorldBridge) this.level).bridge$dimension();
    }

    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        return ((WorldBridge) this.level).bridge$entities(entity, radius, predicate);
    }

    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return ((WorldBridge) this.level).bridge$entities(entity, pos1, pos2, predicate);
    }
}
