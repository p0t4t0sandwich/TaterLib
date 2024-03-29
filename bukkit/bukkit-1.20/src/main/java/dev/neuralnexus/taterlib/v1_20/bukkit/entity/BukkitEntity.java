package dev.neuralnexus.taterlib.v1_20.bukkit.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.v1_20.bukkit.world.BukkitLocation;
import dev.neuralnexus.taterlib.v1_20.bukkit.world.BukkitWorld;
import dev.neuralnexus.taterlib.world.Location;

import java.util.UUID;

/** Bukkit implementation of {@link Entity}. */
public class BukkitEntity implements Entity {
    private final org.bukkit.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Bukkit entity.
     */
    public BukkitEntity(org.bukkit.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Bukkit entity.
     *
     * @return The Bukkit entity.
     */
    public org.bukkit.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        return entity.getCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new BukkitLocation(entity.getLocation());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        if (entity.getLocation().getWorld() == null) {
            return null;
        }
        return entity.getLocation().getWorld().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.getLocation().getBlock().getBiome().name();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.teleport(
                new org.bukkit.Location(
                        ((BukkitWorld) location.world()).world(),
                        location.x(),
                        location.y(),
                        location.z()));
    }
}
