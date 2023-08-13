package dev.neuralnexus.taterlib.common.event.player;

import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.common.event.api.Event;

/**
 * Player events.
 */
public final class PlayerEvents {
    /**
     * Called when a player advances an advancement.
     */
    public static final Event<PlayerAdvancementEvent> ADVANCEMENT = new Event<>(PlayerAdvancementEvent.class);

    /**
     * Called when a player finishes an advancement.
     */
    public static final Event<PlayerAdvancementFinishedEvent> ADVANCEMENT_FINISHED = new Event<>(PlayerAdvancementFinishedEvent.class);

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
     * Called when a player switches servers.
     */
    public static final Event<PlayerServerSwitchEvent> SERVER_SWITCH = new Event<>(PlayerServerSwitchEvent.class);

    @FunctionalInterface
    public interface PlayerAdvancementEvent {
        void onPlayerAdvancement(AbstractPlayer player, String advancement);
    }

    @FunctionalInterface
    public interface PlayerAdvancementFinishedEvent {
        void onPlayerAdvancementFinished(AbstractPlayer player, String advancement);
    }

    @FunctionalInterface
    public interface PlayerDeathEvent {
        void onPlayerDeath(AbstractPlayer player, String source);
    }

    @FunctionalInterface
    public interface PlayerLoginEvent {
        void onPlayerLogin(AbstractPlayer player);
    }

    @FunctionalInterface
    public interface PlayerLogoutEvent {
        void onPlayerLogout(AbstractPlayer player);
    }

    @FunctionalInterface
    public interface PlayerMessageEvent {
        void onPlayerMessage(AbstractPlayer player, String message, boolean cancelled);
    }

    @FunctionalInterface
    public interface PlayerServerSwitchEvent {
        void onPlayerServerSwitch(AbstractPlayer player, String server);
    }
}
