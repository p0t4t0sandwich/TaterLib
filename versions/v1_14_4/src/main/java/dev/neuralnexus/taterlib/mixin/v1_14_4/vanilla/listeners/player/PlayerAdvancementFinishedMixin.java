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
import dev.neuralnexus.taterlib.v1_14_4.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;

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
@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementFinishedMixin {
    @Shadow private ServerPlayer player;

    @Shadow
    public abstract AdvancementProgress getOrStartProgress(Advancement advancement);

    @Inject(method = "unregisterListeners", at = @At("HEAD"))
    public void onPlayerAdvancementFinished(Advancement advancement, CallbackInfo ci) {
        if (advancement.getDisplay() != null) {
            DisplayInfo display = advancement.getDisplay();
            if (display.shouldAnnounceChat() && getOrStartProgress(advancement).isDone()) {
                PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                        new VanillaPlayerAdvancementEvent.AdvancementFinished(player, advancement));
            }
        }
    }
}
