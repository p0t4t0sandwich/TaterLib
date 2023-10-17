package dev.neuralnexus.taterlib.velocity.abstractions.player;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.common.abstractions.utils.Position;
import dev.neuralnexus.taterlib.velocity.VelocityTaterLibPlugin;
import net.kyori.adventure.text.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Abstracts a Velocity player to an AbstractPlayer.
 */
public class VelocityPlayer implements AbstractPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Velocity player.
     */
    public VelocityPlayer(Player player) {
        this.player = player;
        if (player.getCurrentServer().isPresent()) {
            this.serverName = player.getCurrentServer().get().getServerInfo().getName();
        } else {
            this.serverName = "local";
        }
    }

    /**
     * Constructor.
     * @param player The Velocity player.
     * @param serverName The name of the server the player is on.
     */
    public VelocityPlayer(Player player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the Velocity player
     * @return The Velocity player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Connect the player to a server.
     * @param serverName The name of the server to connect to.
     */
    public void connect(String serverName) {
        if (!VelocityTaterLibPlugin.getProxyServer().getServer(serverName).isPresent()) return;

        RegisteredServer server = VelocityTaterLibPlugin.getProxyServer().getServer(serverName).get();
        player.createConnectionRequest(server).fireAndForget();
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getUsername();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getUsername();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Position getPosition() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerName() {
        return serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServerName(String server) {
        this.serverName = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.text(message));
    }

    /**
     * @inheritDoc
     */
//    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        player.getCurrentServer().ifPresent(serverConnection -> serverConnection.sendPluginMessage(MinecraftChannelIdentifier.from(channel), data));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayerInventory getInventory() {
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void kickPlayer(String message) {
        player.disconnect(Component.text(message));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setSpawn(Position position) {}
}
