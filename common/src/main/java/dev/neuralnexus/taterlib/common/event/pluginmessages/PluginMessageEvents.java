package dev.neuralnexus.taterlib.common.event.pluginmessages;

import dev.neuralnexus.taterlib.common.abstractions.events.pluginmessages.AbstractPluginMessageEvent;
import dev.neuralnexus.taterlib.common.event.api.Event;

public class PluginMessageEvents {
    /**
     * Called when a plugin message is received.
     */
    public static final Event<AbstractPluginMessageEvent> PLUGIN_MESSAGE = new Event<>(AbstractPluginMessageEvent.class);

    /**
     * Called when a plugin message is received from a player.
     */
    public static final Event<AbstractPluginMessageEvent.AbstractPlayerPluginMessageEvent> PLAYER_PLUGIN_MESSAGE = new Event<>(AbstractPluginMessageEvent.AbstractPlayerPluginMessageEvent.class);

    /**
     * Called when a plugin message is received from a server.
     */
    public static final Event<AbstractPluginMessageEvent.AbstractServerPluginMessageEvent> SERVER_PLUGIN_MESSAGE = new Event<>(AbstractPluginMessageEvent.AbstractServerPluginMessageEvent.class);
}
