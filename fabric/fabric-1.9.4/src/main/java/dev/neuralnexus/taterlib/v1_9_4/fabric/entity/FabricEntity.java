package dev.neuralnexus.taterlib.v1_9_4.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.v1_9_4.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_9_4.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.v1_9_4.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.v1_9_4.fabric.world.FabricServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Optional;
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
    public String biome() {
        return entity.world.getBiome(entity.getBlockPos()).getName();
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().dimension().equals(dimension())) {
            Optional<ServerWorld> serverLevel =
                    new FabricServer(FabricTaterLibPlugin.minecraftServer)
                            .world(location.world().dimension())
                            .map(FabricServerWorld.class::cast)
                            .map(FabricServerWorld::world);
            if (!serverLevel.isPresent()) return;
            entity.changeDimension(serverLevel.get().dimension.getDimensionType().getId());
        }
        ((LivingEntity) entity).method_13071(location.x(), location.y(), location.z());
    }
}
