package dev.neuralnexus.taterlib.common.listeners.command;

import dev.neuralnexus.taterlib.common.event.command.BrigadierCommandRegisterEvent;

/**
 * The command listener.
 */
public class CommandListener {
    /**
     * Called when a brigadier command is registered.
     * @param event The event.
     */
    public static void onRegisterBrigadierCommand(BrigadierCommandRegisterEvent event) {
        BrigadierHelperClass.onRegisterBrigadierCommand(event);
    }
}
