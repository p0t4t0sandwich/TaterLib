/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.vanilla.entity.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.resource.ResourceKey;
import dev.neuralnexus.taterlib.v1_20.vanilla.entity.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.network.VanillaCustomPacketPayload_1_20_2;

import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;

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

    @Override
    public void sendPluginMessage(ResourceKey channel, byte[] data) {
        ((net.minecraft.server.level.ServerPlayer) player)
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new VanillaCustomPacketPayload_1_20_2(channel, data)));
    }

    @Override
    public int ping() {
        return ((net.minecraft.server.level.ServerPlayer) player).connection.latency();
    }
}
