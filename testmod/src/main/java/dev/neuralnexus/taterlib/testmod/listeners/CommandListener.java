/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.testmod.listeners;

import dev.neuralnexus.taterapi.command.Command;
import dev.neuralnexus.taterapi.event.command.BrigadierCommandRegisterEvent;
import dev.neuralnexus.taterapi.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.testmod.commands.TestModCommand;
import dev.neuralnexus.taterapi.loader.Loader;

/** The command listener. */
public class CommandListener {
    /**
     * Called when commands are being registered.
     *
     * @param event The event.
     */
    public static void onRegisterCommand(CommandRegisterEvent event) {
        //        event.registerCommand(Example.plugin(), new ExampleCommand(), "alias1",
        // "alias2");
    }

    /**
     * Called when brigadier commands are being registered.
     *
     * @param event The event.
     */
    public static void onRegisterBrigadierCommand(BrigadierCommandRegisterEvent event) {
        Command command = new TestModCommand();
        BrigadierHelperClass.onRegisterBrigadierCommand(
                event, command, Loader.instance().plugin(), command.name(), "ex", "alias2");
    }
}
