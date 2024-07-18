/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15.vanilla.event.command;

import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_15.vanilla.command.VanillaCommandSender;
import dev.neuralnexus.taterlib.v1_15.vanilla.entity.player.VanillaPlayer;

import net.minecraft.commands.CommandSourceStack;

/** Vanilla implementation of {@link BrigadierCommandRegisterEvent}. */
public class VanillaBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSourceStack> {
    private final CommandDispatcher<CommandSourceStack> dispatcher;
    private final boolean dedicated;

    public VanillaBrigadierCommandRegisterEvent(
            CommandDispatcher<CommandSourceStack> dispatcher, boolean dedicated) {
        this.dispatcher = dispatcher;
        this.dedicated = dedicated;
    }

    @Override
    public boolean isDedicated() {
        return dedicated;
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
        return new VanillaCommandSender(source);
    }

    @Override
    public Player getPlayer(CommandSourceStack source) {
        return new VanillaPlayer((net.minecraft.world.entity.player.Player) source.getEntity());
    }

    @Override
    public boolean isPlayer(CommandSourceStack source) {
        return source.getEntity() instanceof net.minecraft.world.entity.player.Player;
    }
}
