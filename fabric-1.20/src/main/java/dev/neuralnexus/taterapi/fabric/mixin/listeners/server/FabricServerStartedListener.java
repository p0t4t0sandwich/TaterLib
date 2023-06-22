package dev.neuralnexus.taterapi.fabric.mixin.listeners.server;

import dev.neuralnexus.taterapi.common.listeners.server.ServerStartedListener;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for server start and emits an event.
 */
@Mixin(MinecraftServer.class)
public class FabricServerStartedListener implements ServerStartedListener {
    /**
     * Called when the server starts.
     * @param ci The callback info.
     */
    @Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;createMetadata()Lnet/minecraft/server/ServerMetadata;", ordinal = 0))
    public void onServerStarted(CallbackInfo ci) {
        // TODO: Emit event
    }
}
