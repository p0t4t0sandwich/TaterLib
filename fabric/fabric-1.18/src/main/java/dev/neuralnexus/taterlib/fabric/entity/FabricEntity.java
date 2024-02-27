package dev.neuralnexus.taterlib.fabric.entity;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.fabric.FabricTaterLibPlugin;
import dev.neuralnexus.taterlib.fabric.server.FabricServer;
import dev.neuralnexus.taterlib.fabric.world.FabricLocation;
import dev.neuralnexus.taterlib.fabric.world.FabricServerWorld;
import dev.neuralnexus.taterlib.world.Location;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

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
        return entity.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {
        entity.remove(net.minecraft.entity.Entity.RemovalReason.KILLED);
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return entity.getType().toString().split("entity\\.")[1].replace(".", ":");
    }

    /** {@inheritDoc} */
    @Override
    public String customName() {
        if (entity.getCustomName() == null) return null;
        return entity.getCustomName().toString();
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {
        entity.setCustomName(Text.of(name));
    }

    /** {@inheritDoc} */
    @Override
    public Location location() {
        return new FabricLocation(entity);
    }

    /** {@inheritDoc} */
    @Override
    public String dimension() {
        return entity.getEntityWorld().getRegistryKey().getValue().toString();
    }

    /** {@inheritDoc} */
    @Override
    public String biome() {
        return entity.getEntityWorld().getBiome(entity.getBlockPos()).toString();
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
            if (serverLevel.isEmpty()) return;
            if (entity instanceof ServerPlayerEntity player) {
                player.teleport(
                        serverLevel.get(),
                        location.x(),
                        location.y(),
                        location.z(),
                        player.getYaw(),
                        player.getPitch());
                return;
            } else {
                entity.moveToWorld(serverLevel.get());
            }
        }
        entity.teleport(location.x(), location.y(), location.z());
    }
}
