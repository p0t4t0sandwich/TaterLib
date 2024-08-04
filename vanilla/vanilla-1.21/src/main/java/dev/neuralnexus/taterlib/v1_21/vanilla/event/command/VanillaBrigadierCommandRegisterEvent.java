/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_21.vanilla.event.command;

import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/** Vanilla implementation of {@link BrigadierCommandRegisterEvent}. */
public class VanillaBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final CommandDispatcher<CommandSourceStack> dispatcher;
    private final Commands.CommandSelection environment;

    public VanillaBrigadierCommandRegisterEvent(
            CommandDispatcher<CommandSourceStack> dispatcher,
            Commands.CommandSelection environment) {
        this.dispatcher = dispatcher;
        this.environment = environment;
    }

    @Override
    public boolean isDedicated() {
        return environment == Commands.CommandSelection.DEDICATED;
    }

    @Override
    public CommandDispatcher<CommandSourceStack> dispatcher() {
        return dispatcher;
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSourceStack> node,
            String commandName,
            String... aliases) {
        dispatcher.register(node);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(node.build()));
        }
    }

    @Override
    public CommandSender getSender(CommandSourceStack source) {
        return (CommandSender) source;
    }

    @Override
    public Player getPlayer(CommandSourceStack source) {
        return (Player) source.getPlayer();
    }

    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
