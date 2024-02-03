package dev.neuralnexus.taterlib.v1_19.vanilla.event.player;

import dev.neuralnexus.taterlib.event.Cancellable;
import dev.neuralnexus.taterlib.event.player.PlayerMessageEvent;
import dev.neuralnexus.taterlib.player.Player;

import java.util.HashSet;
import java.util.Set;

/** Vanilla implementation of {@link PlayerMessageEvent}. */
public class VanillaPlayerMessageEvent extends VanillaPlayerEvent implements PlayerMessageEvent {
    private final Cancellable cancel;
    private String message;

    public VanillaPlayerMessageEvent(
            net.minecraft.world.entity.player.Player player, String message, Cancellable cancel) {
        super(player);
        this.message = message;
        this.cancel = cancel;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isCancelled() {
        return cancel.isCancelled();
    }

    /** {@inheritDoc} */
    @Override
    public void setCancelled(boolean cancelled) {
        cancel.setCancelled(cancelled);
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        return message;
    }

    /** {@inheritDoc} */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Player> recipients() {
        return new HashSet<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setRecipients(Set<Player> recipients) {}
}
