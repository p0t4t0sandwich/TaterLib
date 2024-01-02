package dev.neuralnexus.taterlib.vanilla.mixin.player;

import dev.neuralnexus.taterlib.vanilla.player.VanillaPlayer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(VanillaPlayer.class)
public abstract class VanillaPlayerMixin_1_20_2 {
    @Shadow @Final private Player player;

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public void sendPluginMessage(String channel, byte[] data) {
        ((ServerPlayer) player)
                .connection.send(
                        new ClientboundCustomPayloadPacket(
                                new CustomPacketPayload() {
                                    @Override
                                    public void write(@NotNull FriendlyByteBuf byteBuf) {
                                        byteBuf.writeBytes(data);
                                    }

                                    @Override
                                    public @NotNull ResourceLocation id() {
                                        String[] channelParts = channel.split(":");
                                        if (channelParts.length == 1) {
                                            return new ResourceLocation(
                                                    "tl-user-forgot", channelParts[0]);
                                        }
                                        return new ResourceLocation(
                                                channelParts[0], channelParts[1]);
                                    }
                                }));
    }

    /**
     * @author Dylan Sperrer (p0t4t0sandwich)
     * @reason Patch for 1.20.2
     */
    @Overwrite
    public int getPing() {
        return ((ServerPlayer) player).connection.latency();
    }
}
