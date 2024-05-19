package dev.neuralnexus.taterlib.v1_20_6.vanilla.player;

import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;

import dev.neuralnexus.taterlib.v1_20_6.vanilla.network.VanillaCustomPacketPayload_1_20_6;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;

/** Vanilla implementation of {@link Player}. */
public class VanillaPlayer_1_20_6 extends VanillaPlayer {
    private final net.minecraft.world.entity.player.Player player;

    /**
     * Constructor.
     *
     * @param player The player.
     */
    public VanillaPlayer_1_20_6(net.minecraft.world.entity.player.Player player) {
        super(player);
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public void sendPluginMessage(String channel, byte[] data) {
        ((ServerPlayer) player)
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new VanillaCustomPacketPayload_1_20_6(channel, data)));
    }
}
