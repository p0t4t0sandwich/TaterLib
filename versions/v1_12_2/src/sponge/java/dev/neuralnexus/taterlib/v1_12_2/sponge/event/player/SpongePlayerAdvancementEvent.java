/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_12_2.sponge.event.player;

import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.v1_8_9.sponge.entity.player.SpongePlayer;

import org.spongepowered.api.advancement.DisplayInfo;
import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/** Sponge implementation of {@link PlayerAdvancementEvent}. */
public class SpongePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final AdvancementEvent.Grant event;

    public SpongePlayerAdvancementEvent(AdvancementEvent.Grant event) {
        this.event = event;
    }

    @Override
    public String advancement() {
        DisplayInfo display = event.getAdvancement().getDisplayInfo().orElse(null);
        if (display != null && display.doesAnnounceToChat()) {
            return display.getTitle().toString();
        } else {
            return event.getAdvancement().getName();
        }
    }

    @Override
    public Player player() {
        return new SpongePlayer(event.getTargetEntity());
    }

    /** Sponge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}. */
    public static class AdvancementFinished extends SpongePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent.Grant event) {
            super(event);
        }
    }

    /** Sponge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}. */
    public static class AdvancementProgress extends SpongePlayerAdvancementEvent
            implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEvent.Grant event;

        public AdvancementProgress(AdvancementEvent.Grant event) {
            super(event);
            this.event = event;
        }

        @Override
        public Collection<String> criterion() {
            return new HashSet<>();
        }
    }
}
