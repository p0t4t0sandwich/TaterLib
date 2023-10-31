package dev.neuralnexus.taterlib.sponge.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerAdvancementEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.player.SpongePlayer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.spongepowered.api.event.advancement.AdvancementEvent;

import java.util.Collection;
import java.util.HashSet;

/**
 * Sponge implementation of {@link AbstractPlayerAdvancementEvent}.
 */
public class SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementEvent {
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
    public AbstractPlayer getPlayer() {
        return new SpongePlayer(event.player());
    }

    /**
     * Sponge implementation of {@link AbstractPlayerAdvancementFinishedEvent}.
     */
    public static class SpongePlayerAdvancementFinishedEvent extends SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementFinishedEvent {
        public SpongePlayerAdvancementFinishedEvent(AdvancementEvent.Grant event) {
            super(event);
        }
    }

    /**
     * Sponge implementation of {@link AbstractPlayerAdvancementProgressEvent}.
     */
    public static class SpongePlayerAdvancementProgressEvent extends SpongePlayerAdvancementEvent implements AbstractPlayerAdvancementProgressEvent {
        private final AdvancementEvent.Grant event;

        public SpongePlayerAdvancementProgressEvent(AdvancementEvent.Grant event) {
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
