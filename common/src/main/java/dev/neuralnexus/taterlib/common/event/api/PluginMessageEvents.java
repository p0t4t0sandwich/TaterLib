package dev.neuralnexus.taterlib.common.event.api;

import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvent;
import dev.neuralnexus.taterlib.common.event.pluginmessages.RegisterPluginMessagesEvent;

/**
 * Plugin message events.
 */
public class PluginMessageEvents {
    /**
     * Called when a plugin message is received.
     */
    public static final Event<PluginMessageEvent> PLUGIN_MESSAGE = new Event<>(PluginMessageEvent.class);

    /**
     * Called when a plugin message is received from a player.
     */
    public static final Event<PluginMessageEvent.Player> PLAYER_PLUGIN_MESSAGE = new Event<>(PluginMessageEvent.Player.class);

    /**
     * Called when a plugin message is received from a server.
     */
    public static final Event<PluginMessageEvent.Server> SERVER_PLUGIN_MESSAGE = new Event<>(PluginMessageEvent.Server.class);

    /**
     * Called when plugin messages channels are registered.
     */
    public static final Event<RegisterPluginMessagesEvent> REGISTER_PLUGIN_MESSAGES = new Event<>(RegisterPluginMessagesEvent.class);
}
