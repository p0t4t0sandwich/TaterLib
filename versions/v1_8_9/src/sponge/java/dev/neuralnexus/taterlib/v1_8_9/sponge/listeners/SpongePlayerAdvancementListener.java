/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.sponge.listeners;

import dev.neuralnexus.taterapi.event.api.PlayerEvents;
import dev.neuralnexus.taterlib.v1_8_9.sponge.event.player.SpongePlayerAdvancementEvent;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.achievement.GrantAchievementEvent;

public class SpongePlayerAdvancementListener {
    /**
     * Called when a player progresses in an advancement.
     *
     * @param event The event.
     */
    @Listener
    public void onPlayerAdvancement(GrantAchievementEvent.TargetPlayer event) {
        PlayerEvents.ADVANCEMENT_FINISHED.invoke(
                new SpongePlayerAdvancementEvent.AdvancementFinished(event));
        PlayerEvents.ADVANCEMENT_PROGRESS.invoke(
                new SpongePlayerAdvancementEvent.AdvancementProgress(event));
    }
}
