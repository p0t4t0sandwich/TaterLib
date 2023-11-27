package dev.neuralnexus.taterlib.velocity.event.pluginmessages;

import com.velocitypowered.api.proxy.ServerConnection;

import dev.neuralnexus.taterlib.common.event.pluginmessages.PluginMessageEvent;

/** Velocity implementation of {@link PluginMessageEvent}. */
public class VelocityPluginMessageEvent implements PluginMessageEvent {
    private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

    public VelocityPluginMessageEvent(
            com.velocitypowered.api.event.connection.PluginMessageEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String getChannel() {
        return event.getIdentifier().getId();
    }

    /** {@inheritDoc} */
    @Override
    public byte[] getData() {
        return event.getData();
    }

    /** Velocity implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends VelocityPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

        public Player(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public dev.neuralnexus.taterlib.common.player.Player getPlayer() {
            return new dev.neuralnexus.taterlib.velocity.player.VelocityPlayer(
                    (com.velocitypowered.api.proxy.Player) event.getSource());
        }
    }

    /** Velocity implementation of {@link PluginMessageEvent.Server}. */
    public static class Server extends VelocityPluginMessageEvent
            implements PluginMessageEvent.Server {
        private final com.velocitypowered.api.event.connection.PluginMessageEvent event;

        public Server(com.velocitypowered.api.event.connection.PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /** {@inheritDoc} */
        @Override
        public String getServer() {
            return ((ServerConnection) event.getSource()).getServerInfo().getName();
        }
    }
}
