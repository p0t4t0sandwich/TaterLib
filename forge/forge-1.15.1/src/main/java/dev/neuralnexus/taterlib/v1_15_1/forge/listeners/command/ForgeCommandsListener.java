/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_15_1.forge.listeners.command;

import dev.neuralnexus.taterapi.event.api.CommandEvents;
import dev.neuralnexus.taterlib.v1_15_1.forge.event.command.ForgeBrigadierCommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_15_1.forge.event.command.ForgeCommandRegisterEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/** Forge register commands. */
public class ForgeCommandsListener {
    /**
     * Registers commands.
     *
     * @param event The register commands event.
     */
    @SubscribeEvent
    public void onRegisterBrigadierCommand(FMLServerStartingEvent event) {
        CommandEvents.REGISTER_COMMAND.invoke(new ForgeCommandRegisterEvent(event));
        CommandEvents.REGISTER_BRIGADIER_COMMAND.invoke(
                new ForgeBrigadierCommandRegisterEvent(event));
    }
}
