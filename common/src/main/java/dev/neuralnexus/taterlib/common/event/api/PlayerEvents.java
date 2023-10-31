package dev.neuralnexus.taterlib.common.event.api;

import dev.neuralnexus.taterlib.common.event.player.*;

/**
 * Player events.
 */
public final class PlayerEvents {
    /**
     * Called when a player finishes an advancement.
     */
    public static final Event<PlayerAdvancementEvent.AdvancementFinished> ADVANCEMENT_FINISHED = new Event<>(PlayerAdvancementEvent.AdvancementFinished.class);

    /**
     * Called when a player progresses an advancement.
     */
    public static final Event<PlayerAdvancementEvent.AdvancementProgress> ADVANCEMENT_PROGRESS = new Event<>(PlayerAdvancementEvent.AdvancementProgress.class);

    /**
     * Called when a player dies.
     */
    public static final Event<PlayerDeathEvent> DEATH = new Event<>(PlayerDeathEvent.class);

    /**
     * Called when a player logs in.
     */
    public static final Event<PlayerLoginEvent> LOGIN = new Event<>(PlayerLoginEvent.class);

    /**
     * Called when a player logs out.
     */
    public static final Event<PlayerLogoutEvent> LOGOUT = new Event<>(PlayerLogoutEvent.class);

    /**
     * Called when a player sends a message.
     */
    public static final Event<PlayerMessageEvent> MESSAGE = new Event<>(PlayerMessageEvent.class);

    /**
     * Called when a player respawns.
     */
    public static final Event<PlayerRespawnEvent> RESPAWN = new Event<>(PlayerRespawnEvent.class);

    /**
     * Called when a player switches servers.
     */
    public static final Event<PlayerServerSwitchEvent> SERVER_SWITCH = new Event<>(PlayerServerSwitchEvent.class);
}
