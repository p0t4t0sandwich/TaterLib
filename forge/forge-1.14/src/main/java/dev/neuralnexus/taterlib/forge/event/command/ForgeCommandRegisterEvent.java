package dev.neuralnexus.taterlib.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.command.Sender;
import dev.neuralnexus.taterlib.common.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.command.ForgeSender;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSource> {
    private final FMLServerStartingEvent event;

    public ForgeCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return event.getServer().isDedicatedServer();
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSource> getDispatcher() {
        return event.getCommandDispatcher();
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node,
            Object plugin,
            String commandName,
            String... aliases) {
        event.getCommandDispatcher().register(node);
        for (String alias : aliases) {
            event.getCommandDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public Sender getSender(CommandSource source) {
        return new ForgeSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(CommandSource source) {
        return new ForgePlayer((PlayerEntity) source.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSource> commandNode =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        event.getCommandDispatcher().register(commandNode);
        for (String alias : aliases) {
            event.getCommandDispatcher().register(literal(alias).redirect(commandNode.build()));
        }
    }
}
