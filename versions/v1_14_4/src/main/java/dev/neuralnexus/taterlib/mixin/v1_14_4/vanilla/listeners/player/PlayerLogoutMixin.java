/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_14_4.vanilla.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.AConstraints;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerLogoutEvent;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@AConstraints({
    @AConstraint(
            platform = {Platform.NEOFORGE, Platform.SPONGE},
            invert = true),
    @AConstraint(
            mappings = Mappings.MOJANG,
            version = @Versions(min = MinecraftVersion.V14, max = MinecraftVersion.V20_1))
})
@Mixin(ServerGamePacketListenerImpl.class)
public class PlayerLogoutMixin {
    @Shadow public ServerPlayer player;

    @Inject(method = "onDisconnect", at = @At("HEAD"))
    private void onLogout(Component reason, CallbackInfo ci) {
        PlayerEvents.LOGOUT.invoke(new VanillaPlayerLogoutEvent(this.player));
    }
}
