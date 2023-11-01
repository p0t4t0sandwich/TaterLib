package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.common.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

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
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(new TextComponentString(name));
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
        ResourceLocation resourceLocation = entity.world.dimension.getType().getRegistryName();
        if (resourceLocation == null) return null;
        return resourceLocation.getNamespace() + ":" + resourceLocation.getPath();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        ResourceLocation biomeRegistry = entity.world.getBiome(
                entity.world.getChunk(entity.getPosition()).getPos().asBlockPos()
        ).getRegistryName();
        if (biomeRegistry == null) return null;
        return biomeRegistry.getNamespace() + ":" + biomeRegistry.getPath();
    }
}