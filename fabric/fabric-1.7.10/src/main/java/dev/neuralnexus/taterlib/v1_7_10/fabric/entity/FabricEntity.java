package dev.neuralnexus.taterlib.v1_7_10.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.v1_7_10.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_7_10.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.v1_7_10.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.v1_7_10.fabric.world.FabricServerWorld;
import dev.neuralnexus.taterlib.world.Location;

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
        return entity.getTranslationKey().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getName() == null) return null;
        return entity.getName().asFormattedString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        // TODO: Implement
        // NAME TAGS SUPPORT
        //        entity.setCustomName(name);
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.world.dimension.getName().replace(" ", "_").toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.world.getBiome((int) entity.x, (int) entity.z).name;
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
            entity.teleportToDimension(serverLevel.get().dimension.dimensionType);
        }
        entity.updatePosition(location.x(), location.y(), location.z());
    }
}
