package dev.neuralnexus.taterlib.forge.mixin.listeners.server;

import dev.neuralnexus.taterlib.event.api.ServerEvents;
import dev.neuralnexus.taterlib.vanilla.event.server.VanillaServerStoppedEvent;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/** Mixin for the server stopped listener */
@Mixin(MinecraftServer.class)
public class ServerStoppedMixin_1_20 {
    @Inject(at = @At("TAIL"), method = "stopServer")
    private void afterShutdownServer(CallbackInfo info) {
        ServerEvents.STOPPED.invoke(new VanillaServerStoppedEvent((MinecraftServer) (Object) this));
    }
}
