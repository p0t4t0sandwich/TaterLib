package dev.neuralnexus.taterlib.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.forge.util.ForgeLocation;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.server.MinecraftServer;

import java.util.Arrays;
import java.util.UUID;

/** Forge implementation of {@link Entity}. */
public class ForgeEntity implements Entity {
    private final net.minecraft.entity.Entity entity;

    /**
     * Constructor.
     *
     * @param entity The Forge entity.
     */
    public ForgeEntity(net.minecraft.entity.Entity entity) {
        this.entity = entity;
    }

    /**
     * Gets the Forge entity.
     *
     * @return The Forge entity.
     */
    public net.minecraft.entity.Entity getEntity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return entity.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.setDead();
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return entity.getCommandSenderName().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        if (entity.getFormattedCommandSenderName() == null) return null;
        return entity.getFormattedCommandSenderName().getFormattedText();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        // TODO: IMPLEMENT NAME TAGS SUPPORT
        //        entity.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return new ForgeLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return entity.posX;
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return entity.posY;
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return entity.posZ;
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return entity.rotationPitch;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return entity.rotationYaw;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return entity.worldObj.provider.getDimensionName().replace(" ", "_").toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return entity.worldObj.provider.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ)
                .biomeName;
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.getWorld().equals(getDimension())) {
            MinecraftServer server = ForgeTaterLibPlugin.server;
            if (server == null) return;
            // TODO: Cross version this and add: location.getWorld().split(":")[1]);
            Arrays.stream(server.worldServers)
                    .filter(
                            worldServer ->
                                    worldServer
                                            .provider
                                            .getDimensionName()
                                            .replace(" ", "_")
                                            .toLowerCase()
                                            .equals(location.getWorld()))
                    .findFirst()
                    .ifPresent(
                            worldServer ->
                                    entity.travelToDimension(worldServer.provider.dimensionId));
        }
        entity.setPosition(location.getX(), location.getY(), location.getZ());
    }
}
