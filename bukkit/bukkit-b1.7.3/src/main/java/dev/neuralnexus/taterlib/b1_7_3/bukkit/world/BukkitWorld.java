package dev.neuralnexus.taterlib.b1_7_3.bukkit.world;

import dev.neuralnexus.taterlib.b1_7_3.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.b1_7_3.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** Bukkit implementation of {@link World}. */
public class BukkitWorld implements World {
    private final org.bukkit.World world;

    /**
     * Creates a new world.
     *
     * @param world The Bukkit world.
     */
    public BukkitWorld(org.bukkit.World world) {
        this.world = world;
    }

    /**
     * Gets the Bukkit world.
     *
     * @return The Bukkit world.
     */
    public org.bukkit.World world() {
        return world;
    }

    /** {@inheritDoc} */
    @Override
    public List<Player> players() {
        return world.getPlayers().stream().map(BukkitPlayer::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return world.getName();
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(
            Entity entity, Location pos1, Location pos2, Predicate<Entity> predicate) {
        return world.getEntities().stream()
                .map(BukkitEntity::new)
                .filter(e -> e.location().x() >= pos1.x() && e.location().x() <= pos2.x())
                .filter(predicate)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        return world.getEntities().stream()
                .map(BukkitEntity::new)
                .filter(e -> e.location().distance(entity.location()) <= radius)
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
