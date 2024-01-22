package dev.neuralnexus.taterlib.vanilla.mixin.patch.player;

import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(VanillaPlayer.class)
public abstract class VanillaPlayerMixin_1_20_2 {
    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public void sendPluginMessage(String channel, byte[] data) {
        ((ServerPlayer) ((VanillaPlayer) (Object) this).getPlayer())
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new VanillaCustomPacketPayload_1_20_2(channel, data)));
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
