/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@neuralnexus.dev
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.mixin.v1_21_1.vanilla.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.Mappings;
import dev.neuralnexus.taterapi.meta.anno.AConstraint;
import dev.neuralnexus.taterapi.meta.anno.AConstraints;
import dev.neuralnexus.taterapi.meta.anno.Versions;
import dev.neuralnexus.taterapi.meta.enums.MinecraftVersion;
import dev.neuralnexus.taterapi.meta.enums.Platform;
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerRespawnEvent;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@AConstraints({
    @AConstraint(
            platform = {Platform.FORGE, Platform.NEOFORGE, Platform.SPONGE},
            invert = true),
    @AConstraint(mappings = Mappings.MOJANG, version = @Versions(min = MinecraftVersion.V21))
})
@Mixin(PlayerList.class)
public class PlayerRespawnMixin {
    @Inject(method = "respawn", at = @At("HEAD"))
    public void onPlayerRespawn(
            ServerPlayer player,
            boolean alive,
            Entity.RemovalReason removalReason,
            CallbackInfoReturnable<ServerPlayer> cir) {
        PlayerEvents.RESPAWN.invoke(new VanillaPlayerRespawnEvent(player, alive));
    }
}
