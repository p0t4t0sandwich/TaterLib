package dev.neuralnexus.taterlib.bungee.event.pluginmessages;

import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Bungee implementation of {@link PluginMessageEvent}.
 */
public class BungeePluginMessageEvent implements PluginMessageEvent {
    private final net.md_5.bungee.api.event.PluginMessageEvent event;

    public BungeePluginMessageEvent(net.md_5.bungee.api.event.PluginMessageEvent event) {
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
     * Bungee implementation of {@link PluginMessageEvent.Player}.
     */
    public static class Player extends BungeePluginMessageEvent implements PluginMessageEvent.Player {
        private final net.md_5.bungee.api.event.PluginMessageEvent event;

        public Player(net.md_5.bungee.api.event.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public dev.neuralnexus.taterlib.common.player.Player getPlayer() {
            return new dev.neuralnexus.taterlib.bungee.player.BungeePlayer((ProxiedPlayer) event.getReceiver());
        }
    }

    /**
     * Bungee implementation of {@link PluginMessageEvent.Server}.
     */
    public static class Server extends BungeePluginMessageEvent implements PluginMessageEvent.Server {
        private final net.md_5.bungee.api.event.PluginMessageEvent event;

        public Server(net.md_5.bungee.api.event.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String getServer() {
            return ((net.md_5.bungee.api.connection.Server) event.getReceiver()).getInfo().getName();
        }
    }
}
