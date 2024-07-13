/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16_3.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
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

    @Override
    public boolean isDedicated() {
        return event.getEnvironment() == Commands.EnvironmentType.DEDICATED;
    }

    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return event.getDispatcher();
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        event.getDispatcher().register(node);
        for (String alias : aliases) {
            event.getDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    @Override
    public CommandSender getSender(CommandSource source) {
        return new ForgeCommandSender(source);
    }

    @Override
    public Player getPlayer(CommandSource source) {
        return new ForgePlayer((PlayerEntity) source.getEntity());
    }

    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }

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
