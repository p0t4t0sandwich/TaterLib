/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.forge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraftforge.event.entity.player.AchievementEvent;

import java.util.Collection;
import java.util.Collections;

/** Forge implementation of {@link PlayerAdvancementEvent}. */
public class ForgePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final AchievementEvent event;

    public ForgePlayerAdvancementEvent(AchievementEvent event) {
        this.event = event;
    }

    @Override
    public String advancement() {
        return event.achievement.getStatName().getFormattedText();
    }

    @Override
    public Player player() {
        return new WrappedPlayer(event.entityPlayer);
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends ForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AchievementEvent event) {
            super(event);
        }
    }

    /** Forge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends ForgePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AchievementEvent event;

        public AdvancementProgress(AchievementEvent event) {
            super(event);
            this.event = event;
        }

        @Override
        public Collection<String> criterion() {
            return Collections.singleton(this.event.achievement.getStatName().getFormattedText());
        }
    }
}
