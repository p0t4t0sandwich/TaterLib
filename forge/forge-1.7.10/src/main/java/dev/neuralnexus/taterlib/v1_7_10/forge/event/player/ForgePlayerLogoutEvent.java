package dev.neuralnexus.taterlib.v1_7_10.forge.event.player;

import cpw.mods.fml.common.gameevent.PlayerEvent;

import dev.neuralnexus.taterlib.event.player.PlayerLogoutEvent;

/** Forge implementation of {@link PlayerLogoutEvent}. */
public class ForgePlayerLogoutEvent extends ForgePlayerEvent implements PlayerLogoutEvent {
  private final PlayerEvent.PlayerLoggedOutEvent event;
  private String logoutMessage = "";

  public ForgePlayerLogoutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
    super(event);
    this.event = event;
  }

  /** {@inheritDoc} */
  @Override
  public String logoutMessage() {
    if (!this.logoutMessage.isEmpty()) {
      return this.logoutMessage;
    }
    return event.player.getCommandSenderName() + " left the game";
  }

  /** {@inheritDoc} */
  @Override
  public void setLogoutMessage(String message) {
    this.logoutMessage = message;
  }
}
