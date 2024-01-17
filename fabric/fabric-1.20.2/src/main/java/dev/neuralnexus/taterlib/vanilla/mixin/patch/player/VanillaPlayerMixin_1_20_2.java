package dev.neuralnexus.taterlib.vanilla.mixin.patch.player;

import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(VanillaPlayer.class)
public abstract class VanillaPlayerMixin_1_20_2 {
    //    @Shadow @Final private Player player;

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    //    @Overwrite
    //    public void sendPluginMessage(String channel, byte[] data) {
    //        ((ServerPlayer) player)
    //                .connection.send(
    //                        new ClientboundCustomPayloadPacket(
    //                                new VanillaCustomPacketPayload_1_20_2(channel, data)));
    //    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    //    @Overwrite
    //    public int getPing() {
    //        return ((ServerPlayer) player).connection.latency();
    //    }
}
