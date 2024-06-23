package dev.neuralnexus.taterlib.v1_7_10.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerEvent;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_7_10.forge.player.ForgePlayer;

/** Forge implementation of {@link PlayerEvent}. */
public class ForgePlayerEvent implements PlayerEvent {
  private final cpw.mods.fml.common.gameevent.PlayerEvent event;

  public ForgePlayerEvent(cpw.mods.fml.common.gameevent.PlayerEvent event) {
    this.event = event;
  }

  /** {@inheritDoc} */
  @Override
  public Player player() {
    return new ForgePlayer(event.player);
  }
}
