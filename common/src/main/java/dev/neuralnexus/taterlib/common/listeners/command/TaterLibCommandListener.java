package dev.neuralnexus.taterlib.common.listeners.command;

import dev.neuralnexus.taterlib.common.TaterLib;
import dev.neuralnexus.taterlib.common.command.TaterLibCommand;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;

/**
 * The command listener.
 */
public class TaterLibCommandListener {
    /**
     * Called when commands are being registered.
     * @param event The event.
     */
    public static void onRegisterCommand(CommandRegisterEvent event) {
        event.registerCommand(TaterLib.getPlugin(), new TaterLibCommand());
    }
}
