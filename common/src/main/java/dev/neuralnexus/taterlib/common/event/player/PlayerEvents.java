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
    public static final Event<PlayerAdvancementFinishedEvent, AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent> ADVANCEMENT_FINISHED = new Event<>(PlayerAdvancementFinishedEvent.class);

    /**
     * Called when a player progresses an advancement.
     */
    public static final Event<PlayerAdvancementProgressEvent, AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent> ADVANCEMENT_PROGRESS = new Event<>(PlayerAdvancementProgressEvent.class);

    /**
     * Called when a player dies.
     */
    public static final Event<PlayerDeathEvent, AbstractPlayerDeathEvent> DEATH = new Event<>(PlayerDeathEvent.class);

    /**
     * Called when a player logs in.
     */
    public static final Event<PlayerLoginEvent, AbstractPlayerLoginEvent> LOGIN = new Event<>(PlayerLoginEvent.class);

    /**
     * Called when a player logs out.
     */
    public static final Event<PlayerLogoutEvent, AbstractPlayerLogoutEvent> LOGOUT = new Event<>(PlayerLogoutEvent.class);

    /**
     * Called when a player sends a message.
     */
    public static final Event<PlayerMessageEvent, AbstractPlayerMessageEvent> MESSAGE = new Event<>(PlayerMessageEvent.class);

    /**
     * Called when a player respawns.
     */
    public static final Event<PlayerRespawnEvent, AbstractPlayerRespawnEvent> RESPAWN = new Event<>(PlayerRespawnEvent.class);

    /**
     * Called when a player switches servers.
     */
    public static final Event<PlayerServerSwitchEvent> SERVER_SWITCH = new Event<>(PlayerServerSwitchEvent.class);

    @FunctionalInterface
    public interface PlayerAdvancementProgressEvent {
        void onPlayerAdvancementProgress(AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementProgressEvent event);
    }

    @FunctionalInterface
    public interface PlayerAdvancementFinishedEvent {
        void onPlayerAdvancementFinished(AbstractPlayerAdvancementEvent.AbstractPlayerAdvancementFinishedEvent event);
    }

    @FunctionalInterface
    public interface PlayerDeathEvent {
        void onPlayerDeath(AbstractPlayerDeathEvent event);
    }

    @FunctionalInterface
    public interface PlayerLoginEvent {
        void onPlayerLogin(AbstractPlayerLoginEvent event);
    }

    @FunctionalInterface
    public interface PlayerLogoutEvent {
        void onPlayerLogout(AbstractPlayerLogoutEvent event);
    }

    @FunctionalInterface
    public interface PlayerMessageEvent {
        void onPlayerMessage(AbstractPlayerMessageEvent event);
    }

    @FunctionalInterface
    public interface PlayerRespawnEvent {
        void onPlayerRespawn(AbstractPlayerRespawnEvent event);
    }

    @FunctionalInterface
    public interface PlayerServerSwitchEvent {
        void onPlayerServerSwitch(AbstractPlayer player, String server);
    }
}
