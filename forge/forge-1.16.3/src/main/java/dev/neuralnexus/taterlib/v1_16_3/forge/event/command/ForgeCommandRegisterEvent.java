/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_3.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_16_3.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_16_3.forge.player.ForgePlayer;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.RegisterCommandsEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSource> {
    private final RegisterCommandsEvent event;

    public ForgeCommandRegisterEvent(RegisterCommandsEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return event.getEnvironment() == Commands.EnvironmentType.DEDICATED;
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return event.getDispatcher();
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        event.getDispatcher().register(node);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(CommandSource source) {
        return new ForgeCommandSender(source);
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
    @SuppressWarnings("unchecked")
    @Override
    public void registerCommand(Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSource> commandNode =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        event.getDispatcher().register(commandNode);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(commandNode.build()));
        }
    }
}
