/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_17.sponge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_17.sponge.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/** Sponge implementation of {@link PlayerAdvancementEvent}. */
public class SpongePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public SpongePlayerAdvancementEvent(AdvancementEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public String advancement() {
        return PlainTextComponentSerializer.plainText()
                .serialize(event.advancement().asComponent());
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer(event.player());
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

        /** {@inheritDoc} */
        @Override
        public Collection<String> criterion() {
            return new HashSet<>();
        }
    }
}
