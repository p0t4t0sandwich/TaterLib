package dev.neuralnexus.taterlib.velocity.event.api.pluginmessages;

import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.neuralnexus.taterlib.common.event.pluginmessages.AbstractPluginMessageEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.velocity.player.VelocityPlayer;

/**
 * Velocity implementation of {@link AbstractPluginMessageEvent}.
 */
public class VelocityPluginMessageEvent implements AbstractPluginMessageEvent {
    private final PluginMessageEvent event;

    public VelocityPluginMessageEvent(PluginMessageEvent event) {
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
     * Velocity implementation of {@link AbstractPlayerPluginMessageEvent}.
     */
    public static class VelocityPlayerPluginMessageEvent extends VelocityPluginMessageEvent implements AbstractPlayerPluginMessageEvent {
        private final PluginMessageEvent event;

        public VelocityPlayerPluginMessageEvent(PluginMessageEvent event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public AbstractPlayer getPlayer() {
            return new VelocityPlayer((Player) event.getSource());
        }
    }

    /**
     * Velocity implementation of {@link AbstractServerPluginMessageEvent}.
     */
    public static class VelocityServerPluginMessageEvent extends VelocityPluginMessageEvent implements AbstractServerPluginMessageEvent {
        private final PluginMessageEvent event;

        public VelocityServerPluginMessageEvent(PluginMessageEvent event) {
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
