/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.listeners.server;

import dev.neuralnexus.taterapi.event.api.ServerEvents;
import dev.neuralnexus.taterapi.event.server.ServerStoppingEvent;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMCVersion;
import dev.neuralnexus.taterapi.muxins.annotations.ReqMappings;
import dev.neuralnexus.taterapi.muxins.annotations.ReqPlatform;

import net.minecraft.server.MinecraftServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@ReqMappings(Mappings.MOJANG)
@ReqPlatform(not = {Platform.NEOFORGE, Platform.SPONGE})
@ReqMCVersion(min = MinecraftVersion.V14)
@Mixin(MinecraftServer.class)
public class ServerStoppingMixin {
    @Inject(at = @At("HEAD"), method = "stopServer")
    private void onServerStopping(CallbackInfo info) {
        ServerEvents.STOPPING.invoke(new ServerStoppingEvent() {});
    }
}
