package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.UUID;

/** Fabric implementation of {@link Entity}. */
public class FabricEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Fabric entity.
     */
    public FabricEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Fabric entity.
     *
     * @return The Fabric entity.
     */
    public net.minecraft.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getEntityName().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.world.dimension.getDimensionType().getName().replace(" ", "_").toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.world.getBiome(entity.getBlockPos()).getName();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().equals(dimension())) {
            MinecraftServer server = FabricTaterLibPlugin.minecraftServer;
            if (server == null) return;
            // TODO: Cross version this and add: location.getWorld().split(":")[1]);
            Arrays.stream(server.worlds)
                    .filter(
                            worldServer ->
                                    worldServer
                                            .dimension
                                            .getDimensionType()
                                            .getName()
                                            .replace(" ", "_")
                                            .toLowerCase()
                                            .equals(location.world()))
                    .findFirst()
                    .ifPresent(
                            worldServer ->
                                    entity.changeDimension(
                                            worldServer.dimension.getDimensionType().getId()));
        }
        ((LivingEntity) entity).method_13071(location.x(), location.y(), location.z());
    }
}
