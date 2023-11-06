package dev.neuralnexus.taterlib.forge.event.api.command;

import dev.neuralnexus.taterlib.common.command.Command;
import dev.neuralnexus.taterlib.common.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.forge.command.ForgeCommandWrapper;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * Forge implementation of {@link CommandRegisterEvent}.
 */
public class ForgeCommandRegisterEvent implements CommandRegisterEvent {
    private final FMLServerStartingEvent event;

    public ForgeCommandRegisterEvent(FMLServerStartingEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerCommand(Object plugin, Command command, String... aliases) {
        event.registerServerCommand(new ForgeCommandWrapper(command, aliases));
    }
}
