package dev.neuralnexus.taterlib.vanilla.event.pluginmessages;

import dev.neuralnexus.taterlib.event.pluginmessages.PluginMessageEvent;
import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import io.netty.buffer.Unpooled;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link PluginMessageEvent}. */
public class VanillaPluginMessageEvent_1_20_2 implements PluginMessageEvent {
    private final String channel;
    private final byte[] data;

    public VanillaPluginMessageEvent_1_20_2(ServerboundCustomPayloadPacket packet) {
        this.channel = packet.payload().id().toString();
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.payload().write(buf);
        this.data = buf.array();
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
    public static class Player extends VanillaPluginMessageEvent_1_20_2
            implements PluginMessageEvent.Player {
        private final ServerPlayer player;

        public Player(ServerboundCustomPayloadPacket packet, ServerPlayer player) {
            super(packet);
            this.player = player;
        }

        /** {@inheritDoc} */
        @Override
        public VanillaPlayer getPlayer() {
            return new VanillaPlayer(this.player);
        }
    }
}
