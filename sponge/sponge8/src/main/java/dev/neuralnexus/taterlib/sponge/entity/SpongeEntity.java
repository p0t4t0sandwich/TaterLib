package dev.neuralnexus.taterlib.sponge.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.utils.Location;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.data.Keys;
import org.spongepowered.api.registry.Registry;
import org.spongepowered.api.registry.RegistryTypes;
import org.spongepowered.api.world.biome.Biome;

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
        return entity.uniqueId();
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
        return entity.type().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (!entity.get(Keys.CUSTOM_NAME).isPresent()) {
            return null;
        }
        return entity.get(Keys.CUSTOM_NAME).get().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new SpongeLocation(entity.location());
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.position().x();
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.position().y();
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.position().z();
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        if (!entity.get(Keys.MAP_WORLD).isPresent()) {
            return null;
        }
        return entity.get(Keys.MAP_WORLD).get().asString();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        Biome biome = entity.location().world().biome(entity.location().blockPosition());
        Registry<Biome> registry = entity.location().world().registry(RegistryTypes.BIOME);
        return registry.findValueKey(biome).get().asString();
    }

    @Override
    public void teleport(Location location) {}

    @Override
    public void teleport(Entity entity) {}
}
