package dev.neuralnexus.taterlib.v1_19.vanilla.mixin.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.server.VanillaServerStoppedEvent;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server stopped listener. */
@Mixin(MinecraftServer.class)
public class ServerStoppedMixin_1_19 {
    /** Called when the server has stopped. */
    @Inject(at = @At("TAIL"), method = "stopServer")
    private void onServerStopped(CallbackInfo info) {
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent((MinecraftServer) (Object) this));
    }
}
