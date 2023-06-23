package dev.neuralnexus.taterapi.velocity.player;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import dev.neuralnexus.taterapi.common.player.TaterPlayer;
import dev.neuralnexus.taterapi.velocity.VelocityTaterAPIPlugin;
import net.kyori.adventure.text.Component;

import java.util.UUID;

/**
 * Abstracts a Velocity player to a TaterPlayer.
 */
public class VelocityTaterPlayer implements TaterPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Velocity player.
     */
    public VelocityTaterPlayer(Player player) {
        this.player = player;

        if (player.getCurrentServer().isPresent()) {
            this.serverName = player.getCurrentServer().get().getServerInfo().getName();
        } else {
            this.serverName = null;
        }
    }

    /**
     * Connect the player to a server.
     * @param serverName The name of the server to connect to.
     */
    public void connect(String serverName) {
        if (!VelocityTaterAPIPlugin.getProxyServer().getServer(serverName).isPresent()) return;

        RegisteredServer server = VelocityTaterAPIPlugin.getProxyServer().getServer(serverName).get();
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
    @Override
    public boolean hasPermission(String permission) {
        return player.hasPermission(permission);
    }
}
