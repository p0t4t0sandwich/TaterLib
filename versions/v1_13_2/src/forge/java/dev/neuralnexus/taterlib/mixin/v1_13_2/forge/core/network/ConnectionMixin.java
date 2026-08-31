package dev.neuralnexus.taterlib.mixin.v1_13_2.forge.core.network;

import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.network.Protocol;
import dev.neuralnexus.taterlib.network.ConnectionBridge;

import io.netty.util.AttributeKey;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@AConstraint(
        mappings = Mappings.SEARGE,
        version = @Versions(min = MinecraftVersion.V7, max = MinecraftVersion.V13_2))
@Mixin(NetworkManager.class)
public abstract class ConnectionMixin implements ConnectionBridge {
    // spotless:off
    @Shadow @Final public static AttributeKey<EnumConnectionState> PROTOCOL_ATTRIBUTE_KEY;
    // spotless:on

    @Override
    public Protocol bridge$protocol() {
        final Object listener = this.bridge$getPacketListener();
        if (listener == null) {
            return null;
        }
        return Protocol.fromLegacyId(
                this.bridge$channel().attr(PROTOCOL_ATTRIBUTE_KEY).get().getId());
    }
}
