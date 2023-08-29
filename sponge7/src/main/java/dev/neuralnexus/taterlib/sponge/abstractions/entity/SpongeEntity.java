package dev.neuralnexus.taterlib.sponge.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.biome.BiomeType;

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
        return entity.getUniqueId();
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
        return entity.getType().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCustomName() {
        if (!entity.get(Keys.DISPLAY_NAME).isPresent() && entity.get(Keys.CUSTOM_NAME_VISIBLE).isPresent()) {
            return null;
        }
        return entity.get(Keys.DISPLAY_NAME).get().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.offer(Keys.DISPLAY_NAME, Text.of(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return entity.getLocation().getPosition().getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return entity.getLocation().getPosition().getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return entity.getLocation().getPosition().getZ();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDimension() {
        return entity.getWorld().getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        return entity.getWorld().getBiome(entity.getLocation().getBlockPosition()).getId();
    }
}
