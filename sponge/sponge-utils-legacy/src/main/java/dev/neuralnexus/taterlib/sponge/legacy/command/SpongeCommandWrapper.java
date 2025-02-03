/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.sponge.legacy.command;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.TaterLib;
import dev.neuralnexus.taterlib.sponge.legacy.entity.player.SpongePlayer;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

/** Wraps a command callback into a Sponge Command. */
public class SpongeCommandWrapper implements CommandExecutor {
    private final Command.Callback callback;
    private final String commandName;

    public SpongeCommandWrapper(Command.Callback callback, String commandName) {
        this.callback = callback;
        this.commandName = commandName;
    }

    @Override
    @SuppressWarnings({"OptionalGetWithoutIsPresent", "RedundantThrows"})
    public @NotNull CommandResult execute(
            @NotNull CommandSource src, @NotNull CommandContext commandArgs)
            throws CommandException {
        try {
            String[] args = commandArgs.<String>getOne("args").get().split(" ");
            if (src instanceof Player) {
                callback.execute(new SpongePlayer((Player) src), commandName, args);
            }
            callback.execute(new SpongeCommandSender(src), commandName, args);
        } catch (Exception e) {
            TaterLib.logger().error("An exception occurred while executing a command", e);
        }
        return CommandResult.builder().successCount(1).build();
    }
}
