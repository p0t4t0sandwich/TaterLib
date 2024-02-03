package dev.neuralnexus.taterlib.v1_19.vanilla.event.network;

import dev.neuralnexus.taterlib.event.pluginmessages.CustomPayloadWrapper;
import dev.neuralnexus.taterlib.event.pluginmessages.PluginMessageEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.player.VanillaPlayer;

import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PluginMessageEvent}. */
public class VanillaPluginMessageEvent implements PluginMessageEvent {
    private final String channel;
    private final byte[] data;

    public VanillaPluginMessageEvent(String channel, byte[] data) {
        this.channel = channel;
        this.data = data;
    }

    public VanillaPluginMessageEvent(CustomPayloadWrapper packet) {
        this.channel = packet.getChannel();
        this.data = packet.getData();
    }

    /** {@inheritDoc} */
    @Override
    public String getChannel() {
        return this.channel;
    }

    /** {@inheritDoc} */
    @Override
    public byte[] getData() {
        return this.data;
    }

    /** Vanilla implementation of {@link PluginMessageEvent.Player}. */
    public static class Player extends VanillaPluginMessageEvent
            implements PluginMessageEvent.Player {
        private final ServerPlayer player;

        public Player(String channel, byte[] data, ServerPlayer player) {
            super(channel, data);
            this.player = player;
        }

        public Player(CustomPayloadWrapper packet, ServerPlayer player) {
            super(packet);
            this.player = player;
        }

        /** {@inheritDoc} */
        @Override
        public dev.neuralnexus.taterlib.player.Player getPlayer() {
            return new VanillaPlayer(this.player);
        }
    }
}
