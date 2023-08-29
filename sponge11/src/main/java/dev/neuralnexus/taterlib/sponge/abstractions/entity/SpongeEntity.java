package dev.neuralnexus.taterlib.sponge.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.registry.Registry;
import org.spongepowered.api.registry.RegistryType;
import org.spongepowered.api.registry.RegistryTypes;
import org.spongepowered.api.world.biome.Biome;
import org.spongepowered.api.world.biome.Biomes;
import org.spongepowered.api.world.server.ServerWorld;

import java.util.UUID;

/**
 * Abstracts a Sponge entity to an AbstractEntity.
 */
public class SpongeEntity implements AbstractEntity {
    private final Entity entity;

    /**
     * Constructor.
     * @param entity The Sponge entity.
     */
    public SpongeEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.uniqueId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getEntityId() {
        return -1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove() {
        entity.remove();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getType() {
        return entity.type().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCustomName() {
        if (!entity.get(Keys.CUSTOM_NAME).isPresent()) {
            return null;
        }
        return entity.get(Keys.CUSTOM_NAME).get().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.offer(Keys.CUSTOM_NAME, Component.text(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return entity.position().x();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return entity.position().y();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return entity.position().z();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDimension() {
        if (!entity.get(Keys.MAP_WORLD).isPresent()) {
            return null;
        }
        return entity.get(Keys.MAP_WORLD).get().asString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        Biome biome = entity.location().world().biome(entity.location().blockPosition());
        Registry<Biome> registry = entity.location().world().registry(RegistryTypes.BIOME);
        return registry.findValueKey(biome).get().asString();
    }
}
