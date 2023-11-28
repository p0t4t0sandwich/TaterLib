package dev.neuralnexus.taterlib.sponge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;

import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;

import java.util.UUID;

/** Sponge implementation of {@link Entity}. */
public class SpongeEntity implements Entity {
    private final org.spongepowered.api.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Sponge entity.
     */
    public SpongeEntity(org.spongepowered.api.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Sponge entity.
     *
     * @return The Sponge entity.
     */
    public org.spongepowered.api.entity.Entity getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (!entity.get(Keys.DISPLAY_NAME).isPresent()
                && entity.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return null;
        }
        return entity.get(Keys.DISPLAY_NAME).get().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new SpongeLocation(entity.getLocation());
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.getLocation().getPosition().getX();
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.getLocation().getPosition().getY();
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.getLocation().getPosition().getZ();
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return (float) entity.getRotation().getX();
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return (float) entity.getRotation().getY();
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.getWorld().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.getWorld().getBiome(entity.getLocation().getBlockPosition()).getId();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.setLocation(
                new org.spongepowered.api.world.Location<>(
                        entity.getWorld(), location.getX(), location.getY(), location.getZ()));
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {
        this.entity.setLocation(
                new org.spongepowered.api.world.Location<>(
                        ((SpongeEntity) entity).getEntity().getWorld(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ()));
    }
}
