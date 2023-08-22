package dev.neuralnexus.taterlib.fabric.abstractions.entity;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.Optional;
import java.util.UUID;

/**
 * Abstracts a Fabric entity to an AbstractEntity.
 */
public class FabricEntity implements AbstractEntity {
    private final Entity entity;

    /**
     * Constructor.
     * @param entity The Fabric entity.
     */
    public FabricEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUniqueId() {
        return entity.getUuid();
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
        // Turn entity.modid.mob into modid:mob
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getCustomName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(Text.of(name));
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
        return entity.getEntityWorld().getRegistryKey().getValue().toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getBiome() {
        Optional<RegistryKey<Biome>> key = entity.getEntityWorld().getBiome(entity.getBlockPos()).getKey();
        return key.map(biomeRegistryKey -> biomeRegistryKey.getValue().toString()).orElse(null);
    }
}
