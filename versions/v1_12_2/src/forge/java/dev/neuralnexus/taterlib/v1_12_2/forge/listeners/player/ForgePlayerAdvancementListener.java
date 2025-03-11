/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.forge.listeners.player;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterlib.v1_12_2.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancements;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/** Listens for player events. */
public class ForgePlayerAdvancementListener {
    /**
     * Called when a player finishes/progresses in an advancement.
     *
     * @param event The advancement event
     */
    @SubscribeEvent
    public void onPlayerAdvancement(AdvancementEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        event.getEntityPlayer(), event.getAdvancement(), ""));

        MinecraftServer server = (MinecraftServer) MetaAPI.instance().server();
        PlayerAdvancements playerAdvancements =
                server.getPlayerManager().getAdvancements((ServerPlayerEntity) event.getEntity());
        AdvancementProgress progress = playerAdvancements.getProgress(event.getAdvancement());

        if (event.getAdvancement().getInfo() != null
                && event.getAdvancement().getInfo().showInChat()
                && progress.isComplete()) {
            PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                    new VanillaPlayerAdvancementEvent.AdvancementFinished(
                            event.getEntityPlayer(), event.getAdvancement()));
        }
    }
}
