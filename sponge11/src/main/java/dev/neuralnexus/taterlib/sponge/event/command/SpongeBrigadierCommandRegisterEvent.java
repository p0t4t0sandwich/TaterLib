package dev.neuralnexus.taterlib.sponge.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;

/**
 * Sponge implementation of {@link BrigadierCommandRegisterEvent}.
 */
public class SpongeBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<Object> {
    private final RegisterCommandEvent<LiteralCommandNode<?>> event;

    public SpongeBrigadierCommandRegisterEvent(RegisterCommandEvent<LiteralCommandNode<?>> event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDedicated() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDispatcher<Object> getDispatcher() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(LiteralArgumentBuilder<Object> node, Object plugin, String commandName, String... aliases) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sender getSender(Object source) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(Object source) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(Object source) {
        return false;
    }
}
