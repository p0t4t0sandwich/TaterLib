package dev.neuralnexus.taterlib.sponge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;
import dev.neuralnexus.taterlib.utils.Location;

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
    public org.spongepowered.api.entity.Entity entity() {
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
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
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
    public Location location() {
        return new SpongeLocation(entity.getLocation());
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.getWorld().getName();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.getWorld()
                .getBiome(entity.getLocation().getBlockPosition().toVector2())
                .getId();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.setLocation(
                new org.spongepowered.api.world.Location<>(
                        entity.getWorld(), location.x(), location.y(), location.z()));
    }
}
