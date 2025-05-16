/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.command;

import dev.neuralnexus.taterapi.command.Command;

import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.exception.CommandException;
import net.minecraft.server.command.source.CommandSource;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Wraps a command callback into a Vanilla CommandBase. */
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
    public void run(CommandSource source, String[] args) throws CommandException {
        command.execute(new WrappedCommandSource(source), command.name(), args);
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.compareTo((net.minecraft.server.command.Command) o);
    }
}
