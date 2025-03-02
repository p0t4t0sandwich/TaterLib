/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_8_9.forge.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.v1_8_9.forge.entity.player.ForgePlayer;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Wraps a command callback into a Forge CommandBase. */
public class ForgeCommandWrapper extends CommandBase {
    private final Command command;
    private final List<String> aliases;

    public ForgeCommandWrapper(Command command, String... aliases) {
        this.command = command;
        this.aliases = Arrays.stream(aliases).collect(Collectors.toList());
    }

    @Override
    public String getCommandName() {
        return command.name();
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return command.usage();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            command.execute(new ForgePlayer((EntityPlayer) sender), command.name(), args);
        } else {
            command.execute(new ForgeSender(sender), command.name(), args);
        }
    }
}
