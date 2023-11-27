package dev.neuralnexus.taterlib.neoforge.listeners.command;

import dev.neuralnexus.taterlib.common.event.api.CommandEvents;
import dev.neuralnexus.taterlib.neoforge.event.command.NeoForgeBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.neoforge.event.command.NeoForgeCommandRegisterEvent;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/** NeoForge register commands. */
public class NeoForgeCommandsListener {
    /**
     * Registers commands.
     *
     * @param event The register commands event.
     */
    @SubscribeEvent
    public void onRegisterBrigadierCommand(RegisterCommandsEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(new NeoForgeCommandRegisterEvent(event));
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new NeoForgeBrigadierCommandRegisterEvent(event));
    }
}
