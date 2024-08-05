/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.modules.bungeecord.api;

import static dev.neuralnexus.taterlib.modules.bungeecord.api.BungeeMsgType.*;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import dev.neuralnexus.taterapi.MinecraftVersion;
import dev.neuralnexus.taterapi.entity.player.Connection;
import dev.neuralnexus.taterapi.entity.player.SimplePlayer;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.TaterLib;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * API for the BungeeCord module based on <a
 * href="https://www.spigotmc.org/wiki/bukkit-bungee-plugin-messaging-channel/">This wiki entry</a>
 */
@SuppressWarnings("unused")
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
     * Get the BungeeCord channel
     *
     * @return The BungeeCord channel
     */
    public ResourceKey bungeeChannel() {
        if (MinecraftVersion.get().isAtLeast(MinecraftVersion.V1_13)) {
            return ResourceKey.of("bungeecord", "main");
        } else {
            return ResourceKey.unsafeOf("BungeeCord");
        }
    }

    /**
     * Connects a player to said subserver
     *
     * @param conn The player to connect
     * @param server String name of server to connect to, as defined in BungeeCord config.yml
     */
    public void Connect(Connection conn, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(CONNECT.type());
        out.writeUTF(server);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Connect a named player to said subserver
     *
     * @param conn The player sending the plugin message
     * @param target Name of the player to teleport
     * @param server Name of server to connect to, as defined in BungeeCord config.yml
     */
    public void ConnectOther(Connection conn, String target, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(CONNECT_OTHER.type());
        out.writeUTF(target);
        out.writeUTF(server);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get the (real) IP of a player
     *
     * @param conn The player to get the IP of
     */
    public void IP(Connection conn) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(IP.type());
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get the (real) IP of another player
     *
     * @param conn The player sending the plugin message
     * @param target The player to get the IP of
     */
    public void IPOther(Connection conn, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(IP_OTHER.type());
        out.writeUTF(target);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get the amount of players on a certain server, or on ALL the servers
     *
     * @param conn The player sending the plugin message
     * @param server The name of the server to get the player count of, or ALL to get the global
     *     player count
     */
    public void PlayerCount(Connection conn, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(PLAYER_COUNT.type());
        out.writeUTF(server);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get a list of players connected on a certain server, or on ALL the servers
     *
     * @param conn The player sending the plugin message
     * @param server The name of the server to get the list of connected players, or ALL for global
     *     online player list
     */
    public void PlayerList(Connection conn, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(PLAYER_LIST.type());
        out.writeUTF(server);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get the server name of an online player
     *
     * @param conn The player sending the plugin message
     * @param target The player to get the server of
     */
    public void GetPlayerServer(Connection conn, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GET_PLAYER_SERVER.type());
        out.writeUTF(target);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get a list of server name strings, as defined in BungeeCord's config.yml
     *
     * @param conn The player sending the plugin message
     */
    public void GetServers(Connection conn) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GET_SERVERS.type());
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Send a message (as in, a chat message) to the specified player
     *
     * @param conn The player sending the plugin message
     * @param target The player to send the message to
     */
    public void Message(Connection conn, String target, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(MESSAGE.type());
        out.writeUTF(target);
        out.writeUTF(message);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Send a raw message (as in, a chat message) to the specified player. The advantage of this
     * method over Message is that you can include click events and hover events
     *
     * @param conn The player sending the plugin message
     * @param target The player to send the message to
     * @param message The raw message to send
     */
    public void MessageRaw(Connection conn, String target, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(MESSAGE_RAW.type());
        out.writeUTF(target);
        out.writeUTF(message);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Get this server's name, as defined in BungeeCord's config.yml
     *
     * @param conn The player sending the plugin message
     */
    public void GetServer(Connection conn) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(GET_SERVER.type());
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Send a custom plugin message to said server. This is one of the most useful channels ever<br>
     * <b>Remember, the sending and receiving server(s) need to have a player online</b>
     *
     * @param conn The player sending the plugin message
     * @param server server to send to, ALL to send to every server (except the one sending the
     *     plugin message), or ONLINE to send to every server that's online (except the one sending
     *     the plugin message)
     * @param subchannel Subchannel for plugin usage
     * @param message the message to send
     */
    public void Forward(Connection conn, String server, String subchannel, byte[] message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(FORWARD.type());
        out.writeUTF(server);
        out.writeUTF(subchannel);

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.write(message);
        } catch (IOException e) {
            TaterLib.logger().error("Could not encode plugin message", e);
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Send a custom plugin message to specific player
     *
     * @param conn The player sending the plugin message
     * @param subchannel Subchannel for plugin usage
     * @param message the message to send
     */
    public void ForwardToPlayer(Connection conn, String subchannel, byte[] message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(FORWARD_TO_PLAYER.type());
        out.writeUTF(((SimplePlayer) conn).name());
        out.writeUTF(subchannel);

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.write(message);
        } catch (IOException e) {
            TaterLib.logger().error("Could not encode plugin message", e);
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Request the UUID of this player
     *
     * @param conn The player sending the plugin message
     */
    public void UUID(Connection conn) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("UUID");
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Request the UUID of any player connected to the BungeeCord proxy
     *
     * @param conn The player sending the plugin message
     * @param target the name of the player whose UUID you would like
     */
    public void UUIDOther(Connection conn, String target) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("UUIDOther");
        out.writeUTF(target);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Request the IP of any server on this proxy
     *
     * @param conn The player sending the plugin message
     * @param server the name of the server
     */
    public void ServerIP(Connection conn, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(SERVER_IP.type());
        out.writeUTF(server);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Kick any player on this proxy
     *
     * @param conn The player sending the plugin message
     * @param target the name of the player
     * @param reason the reason the player is kicked with
     */
    public void KickPlayer(Connection conn, String target, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(KICK_PLAYER.type());
        out.writeUTF(target);
        out.writeUTF(reason);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }

    /**
     * Kick any player on this proxy with a raw disconnect message, which allows you to use hex
     * colors - like MessageRaw, but for KickPlayer
     *
     * @param conn The player sending the plugin message
     * @param target the name of the player
     * @param reason the reason the player is kicked with
     */
    public void KickPlayerRaw(Connection conn, String target, String reason) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(KICK_PLAYER_RAW.type());
        out.writeUTF(target);
        out.writeUTF(reason);
        conn.sendPluginMessage(bungeeChannel(), out.toByteArray());
    }
}
