/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_11_2.forge.event.command;

import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_11_2.forge.command.ForgeCommandWrapper;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/** Forge implementation of {@link CommandRegisterEvent}. */
public class ForgeCommandRegisterEvent implements CommandRegisterEvent {
    private final FMLServerStartingEvent event;

    public ForgeCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        event.registerServerCommand(new ForgeCommandWrapper(command, aliases));
    }
}
