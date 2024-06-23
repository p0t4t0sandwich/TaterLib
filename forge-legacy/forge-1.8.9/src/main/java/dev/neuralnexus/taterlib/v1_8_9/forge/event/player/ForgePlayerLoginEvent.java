package dev.neuralnexus.taterlib.v1_8_9.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerLoginEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/** Forge implementation of {@link PlayerLoginEvent}. */
public class ForgePlayerLoginEvent extends ForgePlayerEvent implements PlayerLoginEvent {
  private final PlayerEvent.PlayerLoggedInEvent event;
  private String loginMessage = "";

  public ForgePlayerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
    super(event);
    this.event = event;
  }

  /** {@inheritDoc} */
  @Override
  public String loginMessage() {
    if (!this.loginMessage.isEmpty()) {
      return this.loginMessage;
    }
    return event.player.getName() + " joined the game";
  }

  /** {@inheritDoc} */
  @Override
  public void setLoginMessage(String message) {
    this.loginMessage = message;
  }
}
