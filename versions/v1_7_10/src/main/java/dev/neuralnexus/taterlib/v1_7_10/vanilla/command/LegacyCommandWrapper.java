/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_7_10.vanilla.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.v1_7_10.vanilla.entity.player.WrappedPlayer;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.source.CommandSource;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Wraps a command callback into a Vanilla CommandBase. */
public class LegacyCommandWrapper extends AbstractCommand {
    private final Command command;
    private final List<String> aliases;

    public LegacyCommandWrapper(Command command, String... aliases) {
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
    public void run(CommandSource source, String[] args)
            throws net.minecraft.server.command.exception.CommandException {
        if (source instanceof PlayerEntity) {
            command.execute(new WrappedPlayer((PlayerEntity) source), command.name(), args);
        } else {
            command.execute(new WrappedSender(source), command.name(), args);
        }
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.compareTo((net.minecraft.server.command.Command) o);
    }
}
