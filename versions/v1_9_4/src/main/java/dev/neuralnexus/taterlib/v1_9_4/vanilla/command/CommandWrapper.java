/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.vanilla.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.command.WrappedCommandSource;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.exception.CommandException;
import net.minecraft.server.command.source.CommandSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Wraps a command callback into a Vanilla CommandBase. */
@ToBeLibrary("brigadier-general")
public class CommandWrapper extends AbstractCommand {
    private final Command command;
    private final List<String> aliases;

    public CommandWrapper(Command command, String... aliases) {
        this.command = command;
        this.aliases = Arrays.stream(aliases).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return this.command.name();
    }

    @Override
    public String getUsage(CommandSource source) {
        return this.command.usage();
    }

    @Override
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override
    @SuppressWarnings("RedundantThrows")
    public void run(MinecraftServer server, CommandSource source, String[] args)
            throws CommandException {
        command.execute(new WrappedCommandSource(source), command.name(), args);
    }
}
