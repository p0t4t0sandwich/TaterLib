package dev.neuralnexus.taterlib.v1_19.forge.listeners.command;

import dev.neuralnexus.taterlib.event.api.CommandEvents;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.command.VanillaBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_19.vanilla.event.command.VanillaCommandRegisterEvent;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/** Forge register commands. */
public class ForgeCommandsListener {
    /**
     * Registers commands.
     *
     * @param event The register commands event.
     */
    @SubscribeEvent
    public void onRegisterBrigadierCommand(RegisterCommandsEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(
                new VanillaCommandRegisterEvent(
                        event.getDispatcher(), event.getCommandSelection()));
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new VanillaBrigadierCommandRegisterEvent(
                        event.getDispatcher(), event.getCommandSelection()));
    }
}
