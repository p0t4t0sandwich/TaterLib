/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_16.fabric.event.command;

import static net.minecraft.server.command.CommandManager.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_16.fabric.command.FabricCommandSender;
import dev.neuralnexus.taterlib.v1_16.fabric.player.FabricPlayer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;

/** Fabric implementation of {@link CommandRegisterEvent}. */
public class FabricCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<ServerCommandSource> {
    private final CommandDispatcher<ServerCommandSource> dispatcher;
    private final boolean dedicated;

    public FabricCommandRegisterEvent(
            CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        this.dispatcher = dispatcher;
        this.dedicated = dedicated;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return dedicated;
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<ServerCommandSource> dispatcher() {
        return dispatcher;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(
            LiteralArgumentBuilder<ServerCommandSource> node,
            Object plugin,
            String commandName,
            String... aliases) {
        dispatcher.register(node);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(node.build()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public CommandSender getSender(ServerCommandSource source) {
        return new FabricCommandSender(source);
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer(ServerCommandSource source) {
        return new FabricPlayer((PlayerEntity) source.getEntity());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlayer(ServerCommandSource source) {
        return source.getEntity() instanceof PlayerEntity;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        final LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        dispatcher.register(literalArgumentBuilder);
        for (String alias : aliases) {
            dispatcher.register(literal(alias).redirect(literalArgumentBuilder.build()));
        }
    }
}
