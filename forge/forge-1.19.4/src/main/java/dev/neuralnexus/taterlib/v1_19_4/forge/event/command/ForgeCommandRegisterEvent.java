package dev.neuralnexus.taterlib.v1_19_4.forge.event.command;

import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_19_4.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_19_4.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final RegisterCommandsEvent event;

    public ForgeCommandRegisterEvent(RegisterCommandsEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return event.getCommandSelection() == Commands.CommandSelection.DEDICATED;
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSourceStack> dispatcher() {
        return event.getDispatcher();
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSourceStack> node,
            Object plugin,
            String commandName,
            String... aliases) {
        event.getDispatcher().register(node);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(CommandSourceStack source) {
        return new ForgeCommandSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new ForgePlayer((net.minecraft.world.entity.player.Player) source.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSourceStack> commandNode =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        event.getDispatcher().register(commandNode);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(commandNode.build()));
        }
    }
}
