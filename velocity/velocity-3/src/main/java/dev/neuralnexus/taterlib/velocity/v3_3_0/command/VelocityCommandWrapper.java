/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.velocity.v3_3_0.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.RawCommand;
import com.velocitypowered.api.proxy.Player;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterlib.velocity.v3_3_0.entity.player.VelocityPlayer;

/** Wraps a command callback into a Velocity Command. */
public class VelocityCommandWrapper implements RawCommand {
    private final Command.Callback callback;

    public VelocityCommandWrapper(Command.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource sender = invocation.source();
        if (sender instanceof Player) {
            callback.execute(
                    new VelocityPlayer((Player) sender),
                    invocation.alias(),
                    invocation.arguments().split(" "));
        } else {
            callback.execute(
                    new VelocityCommandSender(sender),
                    invocation.alias(),
                    invocation.arguments().split(" "));
        }
    }
}
