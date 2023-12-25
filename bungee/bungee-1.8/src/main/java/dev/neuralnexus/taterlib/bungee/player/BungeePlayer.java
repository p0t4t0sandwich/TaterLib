package dev.neuralnexus.taterlib.bungee.player;

import dev.neuralnexus.taterlib.bungee.BungeeTaterLibPlugin;
import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;
import dev.neuralnexus.taterlib.player.GameMode;
import dev.neuralnexus.taterlib.player.ProxyPlayer;
import dev.neuralnexus.taterlib.utils.Location;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

/** BungeeCord implementation of {@link ProxyPlayer}. */
public class BungeePlayer implements ProxyPlayer {
    private final ProxiedPlayer player;
    private String serverName;

    /**
     * Constructor.
     *
     * @param player The BungeeCord player.
     */
    public BungeePlayer(ProxiedPlayer player) {
        this.player = player;
        if (player.getServer() != null) {
            this.serverName = player.getServer().getInfo().getName();
        } else {
            this.serverName = "local";
        }
    }

    /**
     * Constructor.
     *
     * @param player The BungeeCord player.
     * @param serverName The name of the server the player is on.
     */
    public BungeePlayer(ProxiedPlayer player, String serverName) {
        this.player = player;
        this.serverName = serverName;
    }

    /**
     * Gets the BungeeCord player
     *
     * @return The BungeeCord player
     */
    public ProxiedPlayer getPlayer() {
        return player;
    }

    /**
     * Connect the player to a server.
     *
     * @param serverName The name of the server to connect to.
     */
    @Override
    public void connect(String serverName) {
        if (BungeeTaterLibPlugin.getProxyServer().getServerInfo(serverName) == null) return;
        ServerInfo server = BungeeTaterLibPlugin.getProxyServer().getServerInfo(serverName);
        player.connect(server);
    }

    /** {@inheritDoc} */
    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    /** {@inheritDoc} */
    @Override
    public String getIPAddress() {
        return player.getAddress().getAddress().getHostAddress();
    }

    /** {@inheritDoc} */
    @Override
    public String getName() {
        return player.getName();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
        return player.getDisplayName();
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
        player.sendMessage(new ComponentBuilder(message).create());
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        player.getServer().getInfo().sendData(channel, data);
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
    public int getPing() {
        return player.getPing();
    }

    /** {@inheritDoc} */
    @Override
    public void kickPlayer(String message) {
        player.disconnect(new ComponentBuilder(message).create());
    }

    /** {@inheritDoc} */
    @Override
    public void setSpawn(Location location, boolean forced) {}

    /** {@inheritDoc} */
    @Override
    public GameMode getGameMode() {
        return GameMode.fromId(0);
    }

    /** {@inheritDoc} */
    @Override
    public void setGameMode(GameMode gameMode) {}

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
}
