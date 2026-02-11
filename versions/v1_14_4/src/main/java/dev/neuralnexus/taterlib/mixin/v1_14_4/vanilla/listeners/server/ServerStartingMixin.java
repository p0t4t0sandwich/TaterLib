/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.listeners.server;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStartingEvent;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.AConstraints;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@AConstraints({
    @AConstraint(
            platform = {Platform.NEOFORGE, Platform.SPONGE},
            invert = true),
    @AConstraint(
            mappings = Mappings.MOJANG,
            version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V15_2))
})
@Mixin(MinecraftServer.class)
public class ServerStartingMixin {
    /**
     * Called when the server is starting. <br>
     * Side Effect: Triggers command registration events
     */
    @Inject(
            method = "run",
            at =
                    @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/server/MinecraftServer;initServer()Z"))
    private void onServerStarting(CallbackInfo info) {
        ServerEvents.STARTING.invoke(new ServerStartingEvent() {});
    }
}
