package dev.neuralnexus.taterapi.fabric.mixin.listeners.server;

import dev.neuralnexus.taterapi.common.listeners.server.TaterServerStoppedListener;
import dev.neuralnexus.taterapi.fabric.events.server.FabricServerStoppedEvent;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for server stop and emits an event.
 */
@Mixin(MinecraftServer.class)
public class FabricServerStoppedListener implements TaterServerStoppedListener {
    /**
     * Called when the server stops.
     * @param ci The callback info.
     */
    @Inject(method = "shutdown", at = @At("HEAD"))
    public void onServerStopped(CallbackInfo ci) {
        // Run the server stopped event
        taterServerStopped();

        MinecraftServer server = (MinecraftServer) (Object) this;
        // Fire the server stopped event
        FabricServerStoppedEvent.EVENT.invoker().onServerStopped(server);
    }
}
