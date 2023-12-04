package dev.neuralnexus.taterlib.velocity.player;

import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.utils.Location;
import dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin;

import net.kyori.adventure.text.Component;

import java.util.UUID;

/** Velocity implementation of {@link ProxyPlayer}. */
public class VelocityPlayer implements ProxyPlayer {
    private final com.velocitypowered.api.proxy.Player player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The Velocity player.
     */
    public VelocityPlayer(com.velocitypowered.api.proxy.Player player) {
        this.player = player;
        if (player.getCurrentServer().isPresent()) {
            this.serverName = player.getCurrentServer().get().getServerInfo().getName();
        } else {
            this.serverName = "local";
        }
    }

    /**
     * Constructor.
     *
     * @param player The Velocity player.
     * @param serverName The name of the server the player is on.
     */
    public VelocityPlayer(com.velocitypowered.api.proxy.Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Velocity player
     *
     * @return The Velocity player
     */
    public com.velocitypowered.api.proxy.Player getPlayer() {
        return player;
    }

    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    @Override
    public void connect(String serverName) {
        if (!VelocityTaterLibPlugin.getProxyServer().getServer(serverName).isPresent()) return;
        RegisteredServer server =
                VelocityTaterLibPlugin.getProxyServer().getServer(serverName).get();
        player.createConnectionRequest(server).fireAndForget();
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public int getEntityId() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void remove() {}

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String getCustomName() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setCustomName(String name) {}

    /** {@inheritDoc} */
    @Override
    public Location getLocation() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public double getX() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public double getY() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public double getZ() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public float getYaw() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public float getPitch() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public String getDimension() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String getBiome() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void teleport(Location location) {}

    /** {@inheritDoc} */
    @Override
    public void teleport(Entity entity) {}

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getUsername();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getUsername();
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
        player.sendMessage(Component.text(message));
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        player.getCurrentServer()
                .ifPresent(
                        serverConnection ->
                                serverConnection.sendPluginMessage(
                                        MinecraftChannelIdentifier.from(channel), data));
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasPermission(int permissionLevel) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public PlayerInventory getInventory() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        player.disconnect(Component.text(message));
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {}

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location) {}
}
