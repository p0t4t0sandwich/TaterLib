package dev.neuralnexus.taterlib.common.event.player;

import java.util.Collection;

/**
 * Abstract class for player advancement events
 */
public interface AbstractPlayerAdvancementEvent extends AbstractPlayerEvent {
    /**
     * Gets the advancement
     * TODO: Abstract advancements further
     * @return The advancement
     */
    String getAdvancement();

    /**
     * Abstract class for player advancement done events
     */
    interface AbstractPlayerAdvancementFinishedEvent extends AbstractPlayerAdvancementEvent {}

    /**
     * Abstract class for player advancement progress events
     */
    interface AbstractPlayerAdvancementProgressEvent extends AbstractPlayerAdvancementEvent {
        /**
         * Gets the criterion
         * @return The criterion
         */
        Collection<String> getCriterion();
    }
}
