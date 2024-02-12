package dev.neuralnexus.taterlib.bukkit.entity;

import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.utils.Location;

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
        return entity.getType().name();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        //        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new BukkitLocation(entity.getLocation());
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return entity.getLocation().getX();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return entity.getLocation().getY();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return entity.getLocation().getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        return entity.getLocation().getYaw();
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        return entity.getLocation().getPitch();
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
                        org.bukkit.Bukkit.getWorld(location.world()),
                        location.x(),
                        location.y(),
                        location.z()));
    }
}
