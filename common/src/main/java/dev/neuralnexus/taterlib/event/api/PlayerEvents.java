package dev.neuralnexus.taterlib.event.api;

import dev.neuralnexus.taterlib.event.player.*;

/** Player events. */
public final class PlayerEvents {
    /** Called when a player finishes an advancement. */
    public static final EventHolder<PlayerAdvancementEvent.AdvancementFinished>
            ADVANCEMENT_FINISHED =
                    new EventHolder<>(PlayerAdvancementEvent.AdvancementFinished.class);

    /** Called when a player progresses an advancement. */
    public static final EventHolder<PlayerAdvancementEvent.AdvancementProgress>
            ADVANCEMENT_PROGRESS =
                    new EventHolder<>(PlayerAdvancementEvent.AdvancementProgress.class);

    /** Called when a player dies. */
    public static final EventHolder<PlayerDeathEvent> DEATH =
            new EventHolder<>(PlayerDeathEvent.class);

    /** Called when a player logs in. */
    public static final EventHolder<PlayerLoginEvent> LOGIN =
            new EventHolder<>(PlayerLoginEvent.class);

    /** Called when a player logs out. */
    public static final EventHolder<PlayerLogoutEvent> LOGOUT =
            new EventHolder<>(PlayerLogoutEvent.class);

    /** Called when a player sends a message. */
    public static final EventHolder<PlayerMessageEvent> MESSAGE =
            new EventHolder<>(PlayerMessageEvent.class);

    /** Called when a player respawns. */
    public static final EventHolder<PlayerRespawnEvent> RESPAWN =
            new EventHolder<>(PlayerRespawnEvent.class);

    /** Called when a player switches servers. */
    public static final EventHolder<PlayerServerSwitchEvent> SERVER_SWITCH =
            new EventHolder<>(PlayerServerSwitchEvent.class);
}
