package dev.neuralnexus.taterlib.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.event.Event;
import dev.neuralnexus.taterlib.player.Player;

/** Abstract class for a brigadier command register event. */
public interface BrigadierCommandRegisterEvent<S> extends Event {
    /**
     * Gets if the server is dedicated.
     *
     * @return If the server is dedicated.
     */
    boolean isDedicated();

    /**
     * Gets the command dispatcher.
     *
     * @return The command dispatcher.
     */
    CommandDispatcher<S> getDispatcher();

    /**
     * Registers a LiteralCommandNode<S>.
     *
     * @param node The node.
     */
    void registerCommand(
            LiteralArgumentBuilder<S> node, Object plugin, String commandName, String... aliases);

    /**
     * Get the sender.
     *
     * @param source The source.
     * @return The sender.
     */
    CommandSender getSender(S source);

    /**
     * Get the player.
     *
     * @param source The source.
     * @return The player.
     */
    Player getPlayer(S source);

    /**
     * Check if the source is a player.
     *
     * @param source The source.
     */
    boolean isPlayer(S source);
}
