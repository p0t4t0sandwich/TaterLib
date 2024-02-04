package dev.neuralnexus.taterlib.v1_20_2.vanilla.mixin.patch.player;

import dev.neuralnexus.taterlib.v1_20.vanilla.player.VanillaPlayer;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.network.VanillaCustomPacketPayload;

import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/** Patch mixin for VanillaPlayer 1.20.2. */
@Mixin(value = VanillaPlayer.class, remap = false)
public class VanillaPlayerMixin_1_20_2 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public void sendPluginMessage(String channel, byte[] data) {
        ((ServerPlayer) ((VanillaPlayer) (Object) this).getPlayer())
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new VanillaCustomPacketPayload(channel, data)));
    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public int getPing() {
        return ((ServerPlayer) ((VanillaPlayer) (Object) this).getPlayer()).connection.latency();
    }
}
