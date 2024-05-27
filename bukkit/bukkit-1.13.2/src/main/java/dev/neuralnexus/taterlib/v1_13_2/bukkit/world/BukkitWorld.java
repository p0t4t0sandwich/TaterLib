package dev.neuralnexus.taterlib.v1_13_2.bukkit.world;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.entity.BukkitEntity;
import dev.neuralnexus.taterlib.v1_13_2.bukkit.player.BukkitPlayer;
import dev.neuralnexus.taterlib.world.Location;
import dev.neuralnexus.taterlib.world.World;

import org.bukkit.util.BoundingBox;

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
        return world
                .getNearbyEntities(
                        new org.bukkit.Location(world, pos1.x(), pos1.y(), pos1.z()),
                        pos1.distance(pos2),
                        pos1.distance(pos2),
                        pos1.distance(pos2),
                        e -> predicate.test(new BukkitEntity(e)))
                .stream()
                .map(BukkitEntity::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public List<Entity> entities(Entity entity, double radius, Predicate<Entity> predicate) {
        return world
                .getNearbyEntities(
                        BoundingBox.of(
                                ((BukkitEntity) entity).entity().getLocation(),
                                radius,
                                radius,
                                radius),
                        e -> predicate.test(new BukkitEntity(e)))
                .stream()
                .map(BukkitEntity::new)
                .collect(Collectors.toList());
    }
}
