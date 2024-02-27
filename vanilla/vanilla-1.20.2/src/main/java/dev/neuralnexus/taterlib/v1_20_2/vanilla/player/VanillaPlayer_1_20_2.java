package dev.neuralnexus.taterlib.v1_20_2.vanilla.player;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.network.VanillaCustomPacketPayload;

import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link Player}. */
public class VanillaPlayer_1_20_2 extends VanillaPlayer {
    private final net.minecraft.world.entity.player.Player player;

    /**
     * Constructor.
     *
     * @param player The player.
     */
    public VanillaPlayer_1_20_2(net.minecraft.world.entity.player.Player player) {
        super(player);
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ((ServerPlayer) player)
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new VanillaCustomPacketPayload(channel, data)));
    }

    /** {@inheritDoc} */
    @Override
    public int ping() {
        return ((ServerPlayer) player).connection.latency();
    }
}
