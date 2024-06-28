/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_14_4.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterlib.command.CommandSender;
import dev.neuralnexus.taterlib.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_14_4.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_14_4.forge.player.ForgePlayer;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeBrigadierCommandRegisterEvent
        implements BrigadierCommandRegisterEvent<CommandSource> {
    private final FMLServerStartingEvent event;

    public ForgeBrigadierCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isDedicated() {
        return event.getServer().isDedicatedServer();
    }

    /** {@inheritDoc} */
    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
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
}
