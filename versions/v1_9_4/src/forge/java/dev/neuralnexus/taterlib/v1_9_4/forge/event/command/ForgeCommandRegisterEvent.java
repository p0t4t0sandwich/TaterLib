/**
 * Copyright (c) 2026 Dylan Sperrer - dylan@sperrer.ca
 * This project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/main/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_9_4.forge.event.command;

import dev.neuralnexus.taterapi.annotations.ToBeLibrary;
import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_9_4.vanilla.command.CommandWrapper;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/** Forge implementation of {@link CommandRegisterEvent}. */
@ToBeLibrary("brigadier-general")
public class ForgeCommandRegisterEvent implements CommandRegisterEvent {
    private final FMLServerStartingEvent event;

    public ForgeCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    @Override
    public void registerCommand(Command command, String... aliases) {
        event.registerServerCommand(new CommandWrapper(command, aliases));
    }
}
