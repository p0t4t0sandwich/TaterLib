package dev.neuralnexus.taterlib.modules.bungeecord.api;

import static dev.neuralnexus.taterlib.modules.bungeecord.api.BungeeMsgType.*;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import dev.neuralnexus.taterlib.player.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * API for the BungeeCord module based on <a
 * href="https://www.spigotmc.org/wiki/bukkit-bungee-plugin-messaging-channel/">This wiki entry</a>
 */
@SuppressWarnings("UnstableApiUsage")
public class BungeeCordAPI {
    private static BungeeCordAPI instance;

    private BungeeCordAPI() {}

    /**
     * Get the instance of the BungeeCord API.
     *
     * @return The instance of the BungeeCord API.
     */
    public static BungeeCordAPI get() {
        if (instance == null) {
            instance = new BungeeCordAPI();
        }
        return instance;
    }

    /**
     * Connects a player to said subserver
     *
     * @param player The player to connect
     * @param server String name of server to connect to, as defined in BungeeCord config.yml
     */
    public void Connect(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(CONNECT.type());
        out.writeUTF(server);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Connect a named player to said subserver
     *
     * @param player The player sending the plugin message
     * @param target Name of the player to teleport
     * @param server Name of server to connect to, as defined in BungeeCord config.yml
     */
    public void ConnectOther(Player player, String target, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(CONNECT_OTHER.type());
        out.writeUTF(target);
        out.writeUTF(server);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get the (real) IP of a player
     *
     * @param player The player to get the IP of
     */
    public void IP(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(IP.type());
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get the (real) IP of another player
     *
     * @param player The player sending the plugin message
     * @param target The player to get the IP of
     */
    public void IPOther(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(IP_OTHER.type());
        out.writeUTF(target);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get the amount of players on a certain server, or on ALL the servers
     *
     * @param player The player sending the plugin message
     * @param server The name of the server to get the player count of, or ALL to get the global
     *     player count
     */
    public void PlayerCount(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(PLAYER_COUNT.type());
        out.writeUTF(server);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get a list of players connected on a certain server, or on ALL of the servers
     *
     * @param player The player sending the plugin message
     * @param server The name of the server to get the list of connected players, or ALL for global
     *     online player list
     */
    public void PlayerList(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(PLAYER_LIST.type());
        out.writeUTF(server);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get the server name of an online player
     *
     * @param player The player sending the plugin message
     * @param target The player to get the server of
     */
    public void GetPlayerServer(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GET_PLAYER_SERVER.type());
        out.writeUTF(target);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get a list of server name strings, as defined in BungeeCord's config.yml
     *
     * @param player The player sending the plugin message
     */
    public void GetServers(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GET_SERVERS.type());
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Send a message (as in, a chat message) to the specified player
     *
     * @param player The player sending the plugin message
     * @param target The player to send the message to
     */
    public void Message(Player player, String target, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(MESSAGE.type());
        out.writeUTF(target);
        out.writeUTF(message);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Send a raw message (as in, a chat message) to the specified player. The advantage of this
     * method over Message is that you can include click events and hover events
     *
     * @param player The player sending the plugin message
     * @param target The player to send the message to
     * @param message The raw message to send
     */
    public void MessageRaw(Player player, String target, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(MESSAGE_RAW.type());
        out.writeUTF(target);
        out.writeUTF(message);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Get this server's name, as defined in BungeeCord's config.yml
     *
     * @param player The player sending the plugin message
     */
    public void GetServer(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GET_SERVER.type());
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Send a custom plugin message to said server. This is one of the most useful channels ever<br>
     * <b>Remember, the sending and receiving server(s) need to have a player online</b>
     *
     * @param player The player sending the plugin message
     * @param server server to send to, ALL to send to every server (except the one sending the
     *     plugin message), or ONLINE to send to every server that's online (except the one sending
     *     the plugin message)
     * @param subchannel Subchannel for plugin usage
     * @param message the message to send
     */
    public void Forward(Player player, String server, String subchannel, byte[] message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(FORWARD.type());
        out.writeUTF(server);
        out.writeUTF(subchannel);

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Send a custom plugin message to specific player
     *
     * @param player The player sending the plugin message
     * @param subchannel Subchannel for plugin usage
     * @param message the message to send
     */
    public void ForwardToPlayer(Player player, String subchannel, byte[] message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(FORWARD_TO_PLAYER.type());
        out.writeUTF(player.name());
        out.writeUTF(subchannel);

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Request the UUID of this player
     *
     * @param player The player sending the plugin message
     */
    public void UUID(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("UUID");
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Request the UUID of any player connected to the BungeeCord proxy
     *
     * @param player The player sending the plugin message
     * @param target the name of the player whose UUID you would like
     */
    public void UUIDOther(Player player, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("UUIDOther");
        out.writeUTF(target);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Request the IP of any server on this proxy
     *
     * @param player The player sending the plugin message
     * @param server the name of the server
     */
    public void ServerIP(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(SERVER_IP.type());
        out.writeUTF(server);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Kick any player on this proxy
     *
     * @param player The player sending the plugin message
     * @param target the name of the player
     * @param reason the reason the player is kicked with
     */
    public void KickPlayer(Player player, String target, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(KICK_PLAYER.type());
        out.writeUTF(target);
        out.writeUTF(reason);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }

    /**
     * Kick any player on this proxy with a raw disconnect message, which allows you to use hex
     * colors - like MessageRaw, but for KickPlayer
     *
     * @param player The player sending the plugin message
     * @param target the name of the player
     * @param reason the reason the player is kicked with
     */
    public void KickPlayerRaw(Player player, String target, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(KICK_PLAYER_RAW.type());
        out.writeUTF(target);
        out.writeUTF(reason);
        player.sendPluginMessage("BungeeCord", out.toByteArray());
    }
}
