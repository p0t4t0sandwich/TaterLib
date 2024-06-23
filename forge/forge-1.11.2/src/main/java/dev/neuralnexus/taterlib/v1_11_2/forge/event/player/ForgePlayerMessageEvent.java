package dev.neuralnexus.taterlib.v1_11_2.forge.event.player;

import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.player.SimplePlayer;
import dev.neuralnexus.taterlib.v1_11_2.forge.player.ForgePlayer;
import java.util.Set;
import net.minecraftforge.event.ServerChatEvent;

/** Forge implementation of {@link PlayerMessageEvent}. */
public class ForgePlayerMessageEvent implements PlayerMessageEvent {
  private final ServerChatEvent event;
  private String message = "";

  public ForgePlayerMessageEvent(ServerChatEvent event) {
    this.event = event;
  }

  /** {@inheritDoc} */
  @Override
  public boolean cancelled() {
    return event.isCanceled();
  }

  /** {@inheritDoc} */
  @Override
  public void setCancelled(boolean cancelled) {
    event.setCanceled(cancelled);
  }

  /** {@inheritDoc} */
  @Override
  public Player player() {
    return new ForgePlayer(event.getPlayer());
  }

  /** {@inheritDoc} */
  @Override
  public String message() {
    if (!this.message.isEmpty()) {
      return this.message;
    }
    return event.getMessage();
  }

  /** {@inheritDoc} */
  @Override
  public void setMessage(String message) {
    this.message = message;
  }

  /** {@inheritDoc} */
  @Override
  public Set<SimplePlayer> recipients() {
    // TODO: Chat recipients module
    throw new VersionFeatureNotSupportedException();
  }

  /** {@inheritDoc} */
  @Override
  public void setRecipients(Set<SimplePlayer> recipients) {
    // TODO: Chat recipients module
    throw new VersionFeatureNotSupportedException();
  }
}
