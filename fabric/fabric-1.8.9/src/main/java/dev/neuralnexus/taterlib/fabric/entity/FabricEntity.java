package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.fabric.util.FabricLocation;
import dev.neuralnexus.taterlib.utils.Location;

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
    public net.minecraft.entity.Entity getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return entity.getUuid();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getTranslationKey().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
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
    public Location getLocation() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.getPos().x;
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.getPos().y;
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.getPos().z;
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return (float) entity.getRotation().x;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return (float) entity.getRotation().y;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.world.dimension.getName().replace(" ", "_").toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.world.getBiome(entity.getBlockPos()).name;
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.getWorld().equals(getDimension())) {
            MinecraftServer server = FabricTaterLibPlugin.server;
            if (server == null) return;
            // TODO: Cross version this and add: location.getWorld().split(":")[1]);
            Arrays.stream(server.worlds)
                    .filter(
                            worldServer ->
                                    worldServer
                                            .dimension
                                            .getName()
                                            .replace(" ", "_")
                                            .toLowerCase()
                                            .equals(location.getWorld()))
                    .findFirst()
                    .ifPresent(
                            worldServer ->
                                    entity.teleportToDimension(worldServer.dimension.getType()));
        }
        entity.updatePosition(location.getX(), location.getY(), location.getZ());
    }
}
