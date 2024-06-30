/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.b1_7_3.bukkit.event.command;

import static dev.neuralnexus.taterlib.bukkit.utils.reflection.Utils.getCommandMap;

import dev.neuralnexus.taterlib.b1_7_3.bukkit.command.BukkitCommandWrapper;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;

/** Bukkit implementation of {@link CommandRegisterEvent}. */
public class BukkitCommandRegisterEvent implements CommandRegisterEvent {
    /** {@inheritDoc} */
    @Override
    public void registerCommand(Command command, String... aliases) {
        getCommandMap().register(command.name(), new BukkitCommandWrapper(command));
    }
}
