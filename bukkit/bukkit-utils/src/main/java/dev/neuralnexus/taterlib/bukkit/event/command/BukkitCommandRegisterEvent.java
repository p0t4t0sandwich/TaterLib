/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.event.command;

import static dev.neuralnexus.taterlib.bukkit.reflection.Utils.getCommandMap;
import static dev.neuralnexus.taterlib.bukkit.reflection.Utils.syncCommands;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterapi.meta.MetaAPI;
import dev.neuralnexus.taterapi.meta.MinecraftVersions;
import dev.neuralnexus.taterlib.bukkit.command.BukkitCommandWrapper;

import java.util.Objects;

/** Bukkit implementation of {@link CommandRegisterEvent}. */
public class BukkitCommandRegisterEvent implements CommandRegisterEvent {
    @Override
    public void registerCommand(Command command, String... aliases) {
        Objects.requireNonNull(getCommandMap());
        // TODO: register(String fallbackPrefix (plugin name), Command command)
        getCommandMap().register(command.name(), new BukkitCommandWrapper(command));
        if (MetaAPI.instance().version().isOlderThan(MinecraftVersions.V7)) {
            return;
        }
        syncCommands();
    }
}
