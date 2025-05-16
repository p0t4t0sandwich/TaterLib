/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.velocity.v3_3_0.command;

import com.velocitypowered.api.command.RawCommand;

import dev.neuralnexus.taterapi.command.Command;

/** Wraps a command callback into a Velocity Command. */
public class VelocityCommandWrapper implements RawCommand {
    private final Command.Callback callback;

    public VelocityCommandWrapper(Command.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void execute(final Invocation invocation) {
        callback.execute(
                new VelocityCommandSource(invocation.source()),
                invocation.alias(),
                invocation.arguments().split(" "));
    }
}
