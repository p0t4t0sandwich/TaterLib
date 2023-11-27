package dev.neuralnexus.taterlib.bukkit.entity;

import dev.neuralnexus.taterlib.bukkit.util.BukkitLocation;
import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.utils.Location;

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

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getType().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        return entity.getType().getName();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        //        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new BukkitLocation(entity.getLocation());
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.getLocation().getX();
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.getLocation().getY();
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.getLocation().getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return entity.getLocation().getYaw();
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return entity.getLocation().getPitch();
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        if (entity.getLocation().getWorld() == null) {
            return null;
        }
        return entity.getLocation().getWorld().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.getLocation().getBlock().getBiome().name();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.teleport(
                new org.bukkit.Location(
                        org.bukkit.Bukkit.getWorld(location.getWorld()),
                        location.getX(),
                        location.getY(),
                        location.getZ()));
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        this.entity.teleport(
                new org.bukkit.Location(
                        org.bukkit.Bukkit.getWorld(entity.getDimension()),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ()));
    }
}
