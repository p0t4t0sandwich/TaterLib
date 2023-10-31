package dev.neuralnexus.taterlib.bungee.event.pluginmessages;

import dev.neuralnexus.taterlib.bungee.player.BungeePlayer;
import dev.neuralnexus.taterlib.common.event.pluginmessages.AbstractPluginMessageEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.PluginMessageEvent;

/**
 * Bungee implementation of {@link AbstractPluginMessageEvent}.
 */
public class BungeePluginMessageEvent implements AbstractPluginMessageEvent {
    private final PluginMessageEvent event;

    public BungeePluginMessageEvent(PluginMessageEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getChannel() {
        return event.getTag();
    }

    /**
     * @inheritDoc
     */
    @Override
    public byte[] getData() {
        return event.getData();
    }

    /**
     * Bungee implementation of {@link AbstractPluginMessageEvent.AbstractPlayerPluginMessageEvent}.
     */
    public static class BungeePlayerPluginMessageEvent extends BungeePluginMessageEvent implements AbstractPluginMessageEvent.AbstractPlayerPluginMessageEvent {
        private final PluginMessageEvent event;

        public BungeePlayerPluginMessageEvent(PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public AbstractPlayer getPlayer() {
            return new BungeePlayer((ProxiedPlayer) event.getReceiver());
        }
    }

    /**
     * Bungee implementation of {@link AbstractPluginMessageEvent.AbstractServerPluginMessageEvent}.
     */
    public static class BungeeServerPluginMessageEvent extends BungeePluginMessageEvent implements AbstractPluginMessageEvent.AbstractServerPluginMessageEvent {
        private final PluginMessageEvent event;

        public BungeeServerPluginMessageEvent(PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String getServer() {
            return ((Server) event.getReceiver()).getInfo().getName();
        }
    }
}
