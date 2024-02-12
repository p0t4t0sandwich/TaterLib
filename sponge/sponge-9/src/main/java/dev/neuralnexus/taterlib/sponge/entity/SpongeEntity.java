package dev.neuralnexus.taterlib.sponge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.sponge.util.SpongeLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.kyori.adventure.text.Component;

import org.spongepowered.api.data.Keys;
import org.spongepowered.api.registry.Registry;
import org.spongepowered.api.registry.RegistryTypes;
import org.spongepowered.api.world.biome.Biome;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3d;

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
        return entity.uniqueId();
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
        return entity.type().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
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
    public Location location() {
        return new SpongeLocation(entity.location());
    }

    /** {@inheritDoc} */
    @Override
    public double x() {
        return entity.position().x();
    }

    /** {@inheritDoc} */
    @Override
    public double y() {
        return entity.position().y();
    }

    /** {@inheritDoc} */
    @Override
    public double z() {
        return entity.position().z();
    }

    /** {@inheritDoc} */
    @Override
    public float yaw() {
        // TODO: Find a way to get the yaw
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public float pitch() {
        // TODO: Find a way to get the pitch
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        if (!entity.get(Keys.MAP_WORLD).isPresent()) {
            return null;
        }
        return entity.get(Keys.MAP_WORLD).get().asString();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        Biome biome = entity.location().world().biome(entity.location().blockPosition());
        Registry<Biome> registry = entity.location().world().registry(RegistryTypes.BIOME);
        return registry.findValueKey(biome).get().asString();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        entity.setLocation(
                ServerLocation.of(
                        (ServerWorld) entity.location().world(),
                        new Vector3d(location.x(), location.y(), location.z())));
    }
}
