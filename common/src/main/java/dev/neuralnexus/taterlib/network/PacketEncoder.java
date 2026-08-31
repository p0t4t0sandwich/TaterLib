package dev.neuralnexus.taterlib.network;

import dev.neuralnexus.taterapi.TaterAPI;
import dev.neuralnexus.taterapi.network.FriendlyByteBuf;
import dev.neuralnexus.taterapi.network.Protocol;
import dev.neuralnexus.taterapi.network.protocol.Packet;

import dev.neuralnexus.taterapi.network.protocol.PacketFlow;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.jspecify.annotations.NonNull;

import static dev.neuralnexus.taterlib.network.ConnectionBridge.HANDLER_PACKET;

public final class PacketEncoder extends MessageToByteEncoder<Packet> {
    public static final String NAME = "taterlib-encoder";

    @SuppressWarnings("RedundantThrows")
    @Override
    protected void encode(
            final @NonNull ChannelHandlerContext ctx,
            final @NonNull Packet msg,
            final @NonNull ByteBuf buf)
            throws Exception {
        try {
            TaterAPI.logger().debug(
                    "Encoding "
                            + msg.getClass().getSimpleName()
                            + " to "
                            + ctx.channel().remoteAddress());
            final FriendlyByteBuf data = new FriendlyByteBuf(buf);

            final ConnectionBridge connection =
                    ((ConnectionBridge) ctx.channel().pipeline().get(HANDLER_PACKET));
            final Protocol protocol = connection.bridge$protocol();
            assert protocol != null;
            int id = protocol.id(PacketFlow.CLIENTBOUND, msg.getClass());

            data.writeVarInt(id);
            msg.type().codec().encode(data, msg);
        } catch (final Exception e) {
            TaterAPI.logger().error("Failed to encode packet " + msg.getClass().getName(), e);
            throw e;
        }
    }
}
