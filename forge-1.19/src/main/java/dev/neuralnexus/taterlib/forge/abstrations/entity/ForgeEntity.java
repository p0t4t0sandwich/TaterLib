package dev.neuralnexus.taterlib.forge.abstrations.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;
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
        return entity.getUUID();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getEntityId() {
        return entity.getId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove() {
        entity.remove(Entity.RemovalReason.KILLED);
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
        entity.setCustomName(Component.nullToEmpty(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getX() {
        return entity.getX();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getY() {
        return entity.getY();
    }

    /**
     * @inheritDoc
     */
    @Override
    public double getZ() {
        return entity.getZ();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDimension() {
        ResourceLocation resourceLocation = entity.level.dimension().registry();
        return resourceLocation.getNamespace() + ":" + resourceLocation.getPath();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        Optional<ResourceKey<Biome>> holder = entity.level.getBiome(entity.blockPosition()).unwrap().left();
        if (!holder.isPresent()) return null;
        ResourceLocation biomeRegistry = holder.get().registry();
        return biomeRegistry.getNamespace() + ":" + biomeRegistry.getPath();
    }
}
