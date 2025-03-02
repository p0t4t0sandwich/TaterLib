/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_20_2.forge;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_20_2.vanilla.event.player.VanillaPlayerAdvancementEvent;

import net.minecraftforge.event.entity.player.AdvancementEvent;

/** Listens for player events. */
public class ForgeAdvancementListener_20_2 {
    /**
     * Called when a player finishes an advancement.
     *
     * @param event The advancement event
     */
    public static void onPlayerAdvancementFinished(AdvancementEvent.AdvancementEarnEvent event) {
        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementFinished(
                        event.getEntity(), event.getAdvancement()));
    }

    /**
     * Called when a player progresses in an advancement.
     *
     * @param event The advancement progress event
     */
    public static void onPlayerAdvancementProgress(
            AdvancementEvent.AdvancementProgressEvent event) {
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new VanillaPlayerAdvancementEvent.AdvancementProgress(
                        event.getEntity(), event.getAdvancement(), event.getCriterionName()));
    }
}
