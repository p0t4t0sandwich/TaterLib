/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.bukkit.utils.event.command;

import static dev.neuralnexus.taterlib.bukkit.utils.reflection.Utils.getCommandMap;
import static dev.neuralnexus.taterlib.bukkit.utils.reflection.Utils.syncCommands;

import dev.neuralnexus.modapi.metadata.MetaAPI;
import dev.neuralnexus.modapi.metadata.MinecraftVersions;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;

import java.util.Objects;
import java.util.function.Function;

/** Bukkit implementation of {@link CommandRegisterEvent}. */
public class BukkitCommandRegisterEvent implements CommandRegisterEvent {
    private final Function<Command, org.bukkit.command.Command> commandWrapper;

    public BukkitCommandRegisterEvent(
            Function<Command, org.bukkit.command.Command> commandWrapper) {
        this.commandWrapper = commandWrapper;
    }

    @Override
    public void registerCommand(Command command, String... aliases) {
        Objects.requireNonNull(getCommandMap());
        // TODO: register(String fallbackPrefix (plugin name), Command command)
        getCommandMap().register(command.name(), commandWrapper.apply(command));
        if (MetaAPI.instance().version().isOlderThan(MinecraftVersions.V7)) {
            return;
        }
        syncCommands();
    }
}
