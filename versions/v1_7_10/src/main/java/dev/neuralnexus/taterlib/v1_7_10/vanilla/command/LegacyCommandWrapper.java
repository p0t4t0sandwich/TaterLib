/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Wraps a command callback into a Vanilla CommandBase. */
public class LegacyCommandWrapper extends CommandBase {
    private final Command command;
    private final List<String> aliases;

    public LegacyCommandWrapper(Command command, String... aliases) {
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
            command.execute(new WrappedPlayer((EntityPlayer) sender), command.name(), args);
        } else {
            command.execute(new WrappedSender(sender), command.name(), args);
        }
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.compareTo((ICommand) o);
    }
}
