package dev.neuralnexus.taterlib.velocity.event.api.pluginmessages;

import com.velocitypowered.api.proxy.ServerConnection;
import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvent;

/**
 * Velocity implementation of {@link PluginMessageEvent}.
 */
public class VelocityPluginMessageEvent implements PluginMessageEvent {
    private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

    public VelocityPluginMessageEvent(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getChannel() {
        return event.getIdentifier().getId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public byte[] getData() {
        return event.getData();
    }

    /**
     * Velocity implementation of {@link Player}.
     */
    public static class VelocityPlayer extends VelocityPluginMessageEvent implements Player {
        private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

        public VelocityPlayer(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public dev.neuralnexus.taterlib.common.player.Player getPlayer() {
            return new dev.neuralnexus.taterlib.velocity.player.VelocityPlayer((com.velocitypowered.api.proxy.Player) event.getSource());
        }
    }

    /**
     * Velocity implementation of {@link Server}.
     */
    public static class VelocityServer extends VelocityPluginMessageEvent implements Server {
        private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

        public VelocityServer(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public String getServer() {
            return ((ServerConnection) event.getSource()).getServerInfo().getName();
        }
    }
}
