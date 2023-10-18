package dev.neuralnexus.taterlib.common.event.pluginmessages;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.event.api.Event;

public class PluginMessageEvents {
    /**
     * Called when a plugin message is received.
     */
    public static final Event<PluginMessageEvent> PLUGIN_MESSAGE = new Event<>(PluginMessageEvent.class);

    /**
     * Called when a plugin message is received from a server.
     */
    public static final Event<PluginMessageEvent.Server> SERVER_PLUGIN_MESSAGE = new Event<>(PluginMessageEvent.Server.class);

    /**
     * Called when a plugin message is received from a player.
     */
    public static final Event<PluginMessageEvent.Player> PLAYER_PLUGIN_MESSAGE = new Event<>(PluginMessageEvent.Player.class);

    @FunctionalInterface
    public interface PluginMessageEvent {
        void onPluginMessage(String channel, byte[] data);

        @FunctionalInterface
        interface Server {
            void onServerPluginMessage(String channel, byte[] data);
        }

        @FunctionalInterface
        interface Player {
            void onPlayerPluginMessage(String channel, AbstractPlayer player, byte[] data);
        }
    }
}
