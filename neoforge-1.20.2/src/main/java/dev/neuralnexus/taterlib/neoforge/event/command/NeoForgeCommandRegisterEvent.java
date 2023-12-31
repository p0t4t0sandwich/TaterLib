package dev.neuralnexus.taterlib.neoforge.event.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.neoforge.command.NeoForgeSender;
import dev.neuralnexus.taterlib.neoforge.player.NeoForgePlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import static net.minecraft.commands.Commands.literal;

/**
 * Forge implementation of {@link BrigadierCommandRegisterEvent}.
 */
public class NeoForgeCommandRegisterEvent implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final RegisterCommandsEvent event;

    public NeoForgeCommandRegisterEvent(RegisterCommandsEvent event) {
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
    public void registerCommand(LiteralArgumentBuilder<CommandSourceStack> node, Object plugin, String commandName, String... aliases) {
        event.getDispatcher().register(node);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(node.build()));
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
        return new NeoForgePlayer((net.minecraft.world.entity.player.Player) source.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSourceStack> commandNode = SimpleBrigadierWrapper.wrapCommand(this, command);
        event.getDispatcher().register(commandNode);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(commandNode.build()));
        }
    }
}
