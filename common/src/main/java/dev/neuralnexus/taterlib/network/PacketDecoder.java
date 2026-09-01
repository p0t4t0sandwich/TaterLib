/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.network;

import static dev.neuralnexus.taterlib.network.ConnectionBridge.HANDLER_PACKET;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.event.api.NetworkEvents;
import dev.neuralnexus.taterapi.event.network.impl.S2CCustomPacketEventImpl;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.network.FriendlyByteBuf;
import dev.neuralnexus.taterapi.network.Protocol;
import dev.neuralnexus.taterapi.network.protocol.PacketFlow;
import dev.neuralnexus.taterapi.network.protocol.PacketType;
import dev.neuralnexus.taterapi.network.protocol.PacketTypes;
import dev.neuralnexus.taterapi.network.protocol.common.ClientboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.network.protocol.common.ServerboundCustomPayloadPacket;
import dev.neuralnexus.taterapi.server.SimpleServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import org.jspecify.annotations.NonNull;

import java.nio.channels.ClosedChannelException;
import java.util.List;

public final class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    public static final String NAME = "taterlib-decoder";

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void decode(
            final @NonNull ChannelHandlerContext ctx,
            final @NonNull ByteBuf msg,
            final List<Object> out)
            throws Exception {
        if (!msg.isReadable()) {
            return;
        }
        final ConnectionBridge connection =
                ((ConnectionBridge) ctx.channel().pipeline().get(HANDLER_PACKET));

        if (connection.bridge$protocol() != Protocol.CONFIGURATION
                && connection.bridge$protocol() != Protocol.PLAY) {
            out.add(msg.retain());
            return;
        }

        final int readerIndex = msg.readerIndex();
        final FriendlyByteBuf data = new FriendlyByteBuf(msg);
        final int id = data.readVarInt();

        final Protocol protocol = connection.bridge$protocol();
        final PacketFlow flow = connection.bridge$flow();
        assert protocol != null;
        assert flow != null;

        PacketType<?> info = protocol.info(flow, id);

        if (info == PacketTypes.COMMON.SERVERBOUND_CUSTOM_PAYLOAD) {
            final FriendlyByteBuf buf = FriendlyByteBuf.wrap(msg);
            final ServerboundCustomPayloadPacket packet =
                    ServerboundCustomPayloadPacket.STREAM_CODEC.decode(buf);
            // final User player = ;
            // NetworkEvents.C2S_CUSTOM_PACKET.invoke(new C2SCustomPacketEventImpl(packet.payload(),
            // player));
            throw new IllegalStateException("NOT IMPLEMENTED"); // TODO: Implement
        } else if (info == PacketTypes.COMMON.CLIENTBOUND_CUSTOM_PAYLOAD) {
            final FriendlyByteBuf buf = FriendlyByteBuf.wrap(msg);
            final ClientboundCustomPayloadPacket packet =
                    ClientboundCustomPayloadPacket.STREAM_CODEC.decode(buf);
            final SimpleServer server = (SimpleServer) MetaAPI.instance().client();
            NetworkEvents.S2C_CUSTOM_PACKET.invoke(
                    new S2CCustomPacketEventImpl(packet.payload(), server));
        } else {
            msg.readerIndex(readerIndex);
        }

        if (msg.isReadable()) {
            out.add(msg.retain());
        }
    }

    @Override
    public void exceptionCaught(
            final @NonNull ChannelHandlerContext ctx, final @NonNull Throwable cause)
            throws Exception {
        if (cause instanceof ClosedChannelException) {
            super.exceptionCaught(ctx, cause);
            return;
        }
        TaterAPI.logger()
                .error(
                        "Exception in PacketDecoder for "
                                + ctx.channel().remoteAddress()
                                + ": "
                                + cause.getMessage(),
                        cause);
        super.exceptionCaught(ctx, cause);
    }
}
