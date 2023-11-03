package dev.neuralnexus.taterlib.common.event.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.player.Player;

/**
 * Abstract class for a brigadier command register event.
 */
public interface BrigadierCommandRegisterEvent<S> {
    /**
     * Gets if the server is dedicated.
     * @return If the server is dedicated.
     */
    boolean isDedicated();

    /**
     * Gets the command dispatcher.
     * @return The command dispatcher.
     */
    CommandDispatcher<S> getDispatcher();

    /**
     * Get the sender.
     * @param source The source.
     * @return The sender.
     */
    Sender getSender(S source);

    /**
     * Get the player.
     * @param source The source.
     * @return The player.
     */
    Player getPlayer(S source);

    /**
     * Check if the source is a player.
     * @param source The source.
     */
    boolean isPlayer(S source);
}
