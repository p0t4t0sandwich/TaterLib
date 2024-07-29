/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13_2.forge.event.command;

import static net.minecraft.command.Commands.literal;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.command.CommandSender;
import dev.neuralnexus.taterapi.command.SimpleBrigadierWrapper;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_13_2.forge.command.ForgeCommandSender;
import dev.neuralnexus.taterlib.v1_13_2.forge.entity.player.ForgePlayer;

import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge implementation of {@link BrigadierCommandRegisterEvent}. */
public class ForgeCommandRegisterEvent
        implements CommandRegisterEvent, BrigadierCommandRegisterEvent<CommandSource> {
    private final FMLServerStartingEvent event;

    public ForgeCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    @Override
    public boolean isDedicated() {
        return event.getServer().isDedicatedServer();
    }

    @Override
    public CommandDispatcher<CommandSource> dispatcher() {
        return event.getCommandDispatcher();
    }

    @Override
    public void registerCommand(
            LiteralArgumentBuilder<CommandSource> node, String commandName, String... aliases) {
        event.getCommandDispatcher().register(node);
        for (String alias : aliases) {
            event.getCommandDispatcher().register(literal(alias).redirect(node.build()));
        }
    }

    @Override
    public CommandSender getSender(CommandSource source) {
        return new ForgeCommandSender(source);
    }

    @Override
    public Player getPlayer(CommandSource source) {
        return new ForgePlayer((EntityPlayer) source.getEntity());
    }

    @Override
    public boolean isPlayer(CommandSource source) {
        return source.getEntity() instanceof EntityPlayer;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void registerCommand(Command command, String... aliases) {
        final LiteralArgumentBuilder<CommandSource> commandNode =
                SimpleBrigadierWrapper.wrapCommand(this, command);
        event.getCommandDispatcher().register(commandNode);
        for (String alias : aliases) {
            event.getCommandDispatcher().register(literal(alias).redirect(commandNode.build()));
        }
    }
}
