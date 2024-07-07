/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.modules.bungeecord.api;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import dev.neuralnexus.taterapi.event.network.PluginMessageEvent;
import dev.neuralnexus.taterlib.modules.bungeecord.api.events.*;

/** Enum for BungeeCord plugin message types. */
public enum BungeeMsgType {
    CONNECT("Connect"),
    CONNECT_OTHER("ConnectOther"),
    IP("IP"),
    IP_OTHER("IPOther"),
    PLAYER_COUNT("PlayerCount"),
    PLAYER_LIST("PlayerList"),
    GET_PLAYER_SERVER("GetPlayerServer"),
    GET_SERVERS("GetServers"),
    MESSAGE("Message"),
    MESSAGE_RAW("MessageRaw"),
    GET_SERVER("GetServer"),
    FORWARD("Forward"),
    FORWARD_TO_PLAYER("ForwardToPlayer"),
    UUID("UUID"),
    UUID_OTHER("UUIDOther"),
    SERVER_IP("ServerIP"),
    KICK_PLAYER("KickPlayer"),
    KICK_PLAYER_RAW("KickPlayerRaw");

    private final String type;

    BungeeMsgType(final String type) {
        this.type = type;
    }

    @SuppressWarnings("UnstableApiUsage")
    public static void Listener(PluginMessageEvent event) {
        if (event.channel().equals("BungeeCord")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(event.data());
            String subchannel = in.readUTF();
            switch (BungeeMsgType.valueOf(subchannel)) {
                case IP:
                    BungeeMessageEvents.IP.invoke(new IPRespEvent(in));
                    break;
                case IP_OTHER:
                    BungeeMessageEvents.IP_OTHER.invoke(new IPOtherRespEvent(in));
                    break;
                case PLAYER_COUNT:
                    BungeeMessageEvents.PLAYER_COUNT.invoke(new PlayerCountRespEvent(in));
                    break;
                case PLAYER_LIST:
                    BungeeMessageEvents.PLAYER_LIST.invoke(new PlayerListRespEvent(in));
                    break;
                case GET_PLAYER_SERVER:
                    BungeeMessageEvents.GET_PLAYER_SERVER.invoke(new GetPlayerServerRespEvent(in));
                    break;
                case GET_SERVERS:
                    BungeeMessageEvents.GET_SERVERS.invoke(new GetServersRespEvent(in));
                    break;
                case GET_SERVER:
                    BungeeMessageEvents.GET_SERVER.invoke(new GetServerRespEvent(in));
                    break;
                case FORWARD:
                    BungeeMessageEvents.FORWARD.invoke(new ForwardRespEvent(in));
                    break;
                case FORWARD_TO_PLAYER:
                    BungeeMessageEvents.FORWARD_TO_PLAYER.invoke(new ForwardToPlayerRespEvent(in));
                    break;
                case UUID:
                    BungeeMessageEvents.UUID.invoke(new UUIDRespEvent(in));
                    break;
                case UUID_OTHER:
                    BungeeMessageEvents.UUID_OTHER.invoke(new UUIDOtherRespEvent(in));
                    break;
                case SERVER_IP:
                    BungeeMessageEvents.SERVER_IP.invoke(new ServerIPRespEvent(in));
                    break;
                default:
                    break;
            }
        }
    }

    public String type() {
        return this.type;
    }
}
