package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.pluginmessages.PluginMessageEvent;
import dev.neuralnexus.taterlib.event.pluginmessages.RegisterPluginMessagesEvent;

/** Plugin message events. */
public class PluginMessageEvents {
    /** Called when a plugin message is received. */
    public static final EventHolder<PluginMessageEvent> PLUGIN_MESSAGE =
            new EventHolder<>(PluginMessageEvent.class);

    /** Called when a plugin message is received from a player. */
    public static final EventHolder<PluginMessageEvent.Player> PLAYER_PLUGIN_MESSAGE =
            new EventHolder<>(PluginMessageEvent.Player.class);

    /** Called when a plugin message is received from a server. */
    public static final EventHolder<PluginMessageEvent.Server> SERVER_PLUGIN_MESSAGE =
            new EventHolder<>(PluginMessageEvent.Server.class);

    /** Called when plugin messages channels are registered. */
    public static final EventHolder<RegisterPluginMessagesEvent> REGISTER_PLUGIN_MESSAGES =
            new EventHolder<>(RegisterPluginMessagesEvent.class);
}
