package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;

import java.util.UUID;

/**
 * Abstracts a Forge entity to an AbstractEntity.
 */
public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Forge entity.
     * @return The Forge entity.
     */
    public net.minecraft.entity.Entity getEntity() {
        return entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove() {
        entity.setDead();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getType() {
        return entity.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCustomName() {
        if (!entity.hasCustomName()) return null;
        return entity.getCustomNameTag();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomNameTag(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return entity.getPosition().getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return entity.getPosition().getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return entity.getPosition().getZ();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDimension() {
        return DimensionType.getById(entity.dimension).getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        ResourceLocation biomeRegistry = entity.world.getBiome(
                entity.world.getChunk(entity.getPosition()).getPos().getBlock((int) getX(), (int) getY(), (int) getZ()
        )).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.getNamespace() + ":" + biomeRegistry.getPath();
    }
}