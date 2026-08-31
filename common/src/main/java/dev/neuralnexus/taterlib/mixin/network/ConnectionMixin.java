package dev.neuralnexus.taterlib.mixin.network;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.AConstraints;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterlib.network.ConnectionBridge;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import org.jspecify.annotations.NonNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("MixinAnnotationTarget")
public final class ConnectionMixin {
    @AConstraints(
            or = {
                @AConstraint(mappings = Mappings.MOJANG),
                @AConstraint(
                        mappings = Mappings.SEARGE,
                        version =
                                @Versions(min = MinecraftVersion.V17, max = MinecraftVersion.V20_4))
            })
    @Mixin(targets = "net.minecraft.network.Connection")
    public abstract static class Mojang implements ConnectionBridge {
        @Shadow
        public abstract Channel shadow$channel();

        @Override
        public @NonNull Channel bridge$channel() {
            return this.shadow$channel();
        }

        @SuppressWarnings("NameDoesntMatchTargetClass")
        @Inject(method = "channelActive", at = @At("TAIL"), remap = false)
        private void onChannelActive(
                final @NonNull ChannelHandlerContext ctx, final @NonNull CallbackInfo ci) {
            ConnectionBridge.injectIntoPipeline(ctx);
        }
    }

    @AConstraint(
            mappings = Mappings.SEARGE,
            version = @Versions(min = MinecraftVersion.V7, max = MinecraftVersion.V16_5))
    @Mixin(targets = "net.minecraft.network.NetworkManager")
    public abstract static class Searge implements ConnectionBridge {
        @Shadow
        public abstract Channel shadow$channel();

        @Override
        public @NonNull Channel bridge$channel() {
            return this.shadow$channel();
        }

        @SuppressWarnings("NameDoesntMatchTargetClass")
        @Inject(method = "channelActive", at = @At("TAIL"), remap = false)
        private void onChannelActive(
                final @NonNull ChannelHandlerContext ctx, final @NonNull CallbackInfo ci) {
            ConnectionBridge.injectIntoPipeline(ctx);
        }
    }
}
