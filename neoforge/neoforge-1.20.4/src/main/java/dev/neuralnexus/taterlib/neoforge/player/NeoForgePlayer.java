package dev.neuralnexus.taterlib.neoforge.player;

import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.neoforge.entity.NeoForgeEntity;
import dev.neuralnexus.taterlib.neoforge.inventory.NeoForgePlayerInventory;
import dev.neuralnexus.taterlib.neoforge.networking.ModMessages;
import dev.neuralnexus.taterlib.neoforge.networking.packet.NeoForgeMessagePacket;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.utils.Location;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.UUID;

/** NeoForge implementation of {@link Player}. */
public class NeoForgePlayer extends NeoForgeEntity implements Player {
    private final net.minecraft.world.entity.player.Player player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Forge player.
     */
    public NeoForgePlayer(net.minecraft.world.entity.player.Player player) {
        super(player);
        this.player = player;
        this.serverName = "local";
    }

    /**
     * Constructor.
     *
     * @param player The Forge player.
     * @param serverName The server name.
     */
    public NeoForgePlayer(net.minecraft.world.entity.player.Player player, String serverName) {
        super(player);
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Forge player
     *
     * @return The Forge player
     */
    public net.minecraft.world.entity.player.Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.getUUID();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public String getServerName() {
        return serverName;
    }

    /** {@inheritDoc} */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /** {@inheritDoc} */
    @Override
    public void sendMessage(String message) {
        player.displayClientMessage(Component.nullToEmpty(message), false);
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ModMessages.sendPluginMessage(
                new NeoForgeMessagePacket(new String(data)), channel, (ServerPlayer) player);
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return new NeoForgePlayerInventory(player.getInventory());
    }

    /** {@inheritDoc} */
    @Override
    public int getPing() {
        return ((ServerPlayer) player).connection.latency();
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        ((ServerPlayer) player).connection.disconnect(Component.nullToEmpty(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {
        ((ServerPlayer) player)
                .setRespawnPosition(
                        Level.OVERWORLD,
                        new BlockPos(
                                (int) location.getX(),
                                (int) location.getY(),
                                (int) location.getZ()),
                        0.0F,
                        forced,
                        false);
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location) {
        setSpawn(location, false);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return player.hasPermissions(permissionLevel);
    }
}
