/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.event.player.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** Player events. */
public final class PlayerEvents {
    /** Called when a player finishes an advancement. */
    public static final EventManager<PlayerAdvancementEvent.AdvancementFinished>
            ADVANCEMENT_FINISHED =
                    new EventManager<>(PlayerAdvancementEvent.AdvancementFinished.class);

    /** Called when a player progresses an advancement. */
    public static final EventManager<PlayerAdvancementEvent.AdvancementProgress>
            ADVANCEMENT_PROGRESS =
                    new EventManager<>(PlayerAdvancementEvent.AdvancementProgress.class);

    /** Called when a player dies. */
    public static final EventManager<PlayerDeathEvent> DEATH =
            new EventManager<>(PlayerDeathEvent.class);

    /** Called when a player logs in. */
    public static final EventManager<PlayerLoginEvent> LOGIN =
            new EventManager<>(PlayerLoginEvent.class);

    /** Called when a player logs out. */
    public static final EventManager<PlayerLogoutEvent> LOGOUT =
            new EventManager<>(PlayerLogoutEvent.class);

    /** Called when a player sends a message. */
    public static final EventManager<PlayerMessageEvent> MESSAGE =
            new EventManager<>(PlayerMessageEvent.class);

    /** Called when a player respawns. */
    public static final EventManager<PlayerRespawnEvent> RESPAWN =
            new EventManager<>(PlayerRespawnEvent.class);

    /** Called when a player switches servers. */
    public static final EventManager<PlayerServerSwitchEvent> SERVER_SWITCH =
            new EventManager<>(PlayerServerSwitchEvent.class);

    /**
     * Gets the events.
     *
     * @return The events.
     */
    public static Set<EventManager<? extends Event>> events() {
        return new HashSet<>(
                Arrays.asList(
                        ADVANCEMENT_FINISHED,
                        ADVANCEMENT_PROGRESS,
                        DEATH,
                        LOGIN,
                        LOGOUT,
                        MESSAGE,
                        RESPAWN,
                        SERVER_SWITCH));
    }
}
