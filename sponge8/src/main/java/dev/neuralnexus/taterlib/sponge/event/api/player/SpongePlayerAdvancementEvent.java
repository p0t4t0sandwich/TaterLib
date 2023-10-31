package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.PlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sponge implementation of {@link PlayerAdvancementEvent}.
 */
public class SpongePlayerAdvancementEvent implements PlayerAdvancementEvent {
    private final AdvancementEvent event;

    public SpongePlayerAdvancementEvent(AdvancementEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getAdvancement() {
        return PlainTextComponentSerializer.plainText().serialize(event.advancement().asComponent());
    }

    /**
     * @inheritDoc
     */
    @Override
    public Player getPlayer() {
        return new SpongePlayer(event.player());
    }

    /**
     * Sponge implementation of {@link PlayerAdvancementEvent.AdvancementFinished}.
     */
    public static class AdvancementFinished extends SpongePlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementFinished {
        public AdvancementFinished(AdvancementEvent.Grant event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link PlayerAdvancementEvent.AdvancementProgress}.
     */
    public static class AdvancementProgress extends SpongePlayerAdvancementEvent implements PlayerAdvancementEvent.AdvancementProgress {
        private final AdvancementEvent.Grant event;

        public AdvancementProgress(AdvancementEvent.Grant event) {
            super(event);
            this.event = event;
        }

        /**
         * @inheritDoc
         */
        @Override
        public Collection<String> getCriterion() {
            return new HashSet<>();
        }
    }
}
