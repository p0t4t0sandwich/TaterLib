package dev.neuralnexus.taterlib.forge.abstrations.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

import java.util.UUID;

/**
 * Abstracts a Forge entity to an AbstractEntity.
 */
public class ForgeEntity implements AbstractEntity {
    private final Entity entity;

    /**
     * Constructor.
     * @param entity The Forge entity.
     */
    public ForgeEntity(Entity entity) {
        this.entity = entity;
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
