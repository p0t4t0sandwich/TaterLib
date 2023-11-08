package dev.neuralnexus.taterlib.neoforge.event.api.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.neoforge.command.NeoForgeSender;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static net.minecraft.commands.Commands.literal;

/**
 * NeoForge implementation of {@link BrigadierCommandRegisterEvent}.
 */
public class NeoForgeBrigadierCommandRegisterEvent implements BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final RegisterCommandsEvent event;

    public NeoForgeBrigadierCommandRegisterEvent(RegisterCommandsEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDedicated() {
        return event.getCommandSelection() == Commands.CommandSelection.DEDICATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandDispatcher<CommandSourceStack> getDispatcher() {
        return event.getDispatcher();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(LiteralCommandNode<CommandSourceStack> node, Object plugin, String commandName, String... aliases) {
        event.getDispatcher().register(node.createBuilder());
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(node));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sender getSender(CommandSourceStack source) {
        return new NeoForgeSender(source);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new NeoForgePlayer(source.getPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
