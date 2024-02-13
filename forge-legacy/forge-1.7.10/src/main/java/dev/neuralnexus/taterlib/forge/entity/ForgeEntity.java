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
    public net.minecraft.entity.Entity entity() {
        return entity;
    }

    /** {@inheritDoc} */
    @Override
    public UUID uuid() {
        return entity.getUniqueID();
    }

    /** {@inheritDoc} */
    @Override
    public int entityId() {
        return entity.getEntityId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.setDead();
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getCommandSenderName().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
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
    public Location location() {
        return new ForgeLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.worldObj.provider.getDimensionName().replace(" ", "_").toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.worldObj.provider.getBiomeGenForCoords((int) entity.posX, (int) entity.posZ)
                .biomeName;
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {
        if (!location.world().equals(dimension())) {
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
                                            .equals(location.world()))
                    .findFirst()
                    .ifPresent(
                            worldServer ->
                                    entity.travelToDimension(worldServer.provider.dimensionId));
        }
        entity.setPosition(location.x(), location.y(), location.z());
    }
}
