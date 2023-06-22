package dev.neuralnexus.taterapi.fabric.mixin.listeners.server;

import dev.neuralnexus.taterapi.common.TaterAPI;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for server stop and emits an event.
 */
@Mixin(MinecraftServer.class)
public class FabricServerStoppedListener {
    /**
     * Called when the server stops.
     * @param ci The callback info.
     */
    @Inject(method = "shutdown", at = @At("HEAD"))
    public void onServerStopped(CallbackInfo ci) {
        TaterAPI.stop();
        // TODO: Emit event
    }
}
