package dev.neuralnexus.taterlib.v1_7_10.forge.event.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import dev.neuralnexus.taterlib.command.Command;
import dev.neuralnexus.taterlib.event.command.CommandRegisterEvent;
import dev.neuralnexus.taterlib.v1_7_10.forge.command.ForgeCommandWrapper;

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
