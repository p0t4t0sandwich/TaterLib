package dev.neuralnexus.taterlib.common.event.player;

import dev.neuralnexus.taterlib.common.abstractions.events.player.*;
import dev.neuralnexus.taterlib.common.event.api.Event;

/**
 * Player events.
 */
public final class PlayerEvents {
    /**
     * Called when a player finishes an advancement.
     */
    public static final Event<AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent> ADVANCEMENT_FINISHED = new Event<>(AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent.class);

    /**
     * Called when a player progresses an advancement.
     */
    public static final Event<AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent> ADVANCEMENT_PROGRESS = new Event<>(AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent.class);

    /**
     * Called when a player dies.
     */
    public static final Event<AbstractPlayerDeathEvent> DEATH = new Event<>(AbstractPlayerDeathEvent.class);

    /**
     * Called when a player logs in.
     */
    public static final Event<AbstractPlayerLoginEvent> LOGIN = new Event<>(AbstractPlayerLoginEvent.class);

    /**
     * Called when a player logs out.
     */
    public static final Event<AbstractPlayerLogoutEvent> LOGOUT = new Event<>(AbstractPlayerLogoutEvent.class);

    /**
     * Called when a player sends a message.
     */
    public static final Event<AbstractPlayerMessageEvent> MESSAGE = new Event<>(AbstractPlayerMessageEvent.class);

    /**
     * Called when a player respawns.
     */
    public static final Event<AbstractPlayerRespawnEvent> RESPAWN = new Event<>(AbstractPlayerRespawnEvent.class);

    /**
     * Called when a player switches servers.
     */
    public static final Event<AbstractPlayerServerSwitchEvent> SERVER_SWITCH = new Event<>(AbstractPlayerServerSwitchEvent.class);
}
