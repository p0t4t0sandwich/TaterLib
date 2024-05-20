package dev.neuralnexus.taterlib.v1_7_10.forge.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.v1_7_10.forge.ForgeTaterLibPlugin;
import dev.neuralnexus.taterlib.v1_7_10.forge.server.ForgeServer;
import dev.neuralnexus.taterlib.v1_7_10.forge.world.ForgeLocation;
import dev.neuralnexus.taterlib.v1_7_10.forge.world.ForgeServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.world.WorldServer;

import java.util.Optional;
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
        // TODO: Implement NAME TAGS SUPPORT
        throw new VersionFeatureNotSupportedException();
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
        if (!location.world().dimension().equals(dimension())) {
            Optional<WorldServer> serverLevel =
                    new ForgeServer(ForgeTaterLibPlugin.minecraftServer)
                            .world(location.world().dimension())
                            .map(ForgeServerWorld.class::cast)
                            .map(ForgeServerWorld::world);
            if (!serverLevel.isPresent()) return;
            entity.travelToDimension(serverLevel.get().provider.dimensionId);
        }
        entity.setPosition(location.x(), location.y(), location.z());
    }
}
