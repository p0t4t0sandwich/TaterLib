package dev.neuralnexus.taterlib.fabric.event.api.player;

import dev.neuralnexus.taterlib.common.event.player.AbstractPlayerMessageEvent;
import dev.neuralnexus.taterlib.common.player.AbstractPlayer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Fabric implementation of {@link AbstractPlayerMessageEvent}.
 */
public class FabricPlayerMessageEvent extends FabricPlayerEvent implements AbstractPlayerMessageEvent {
    private String message;
    private final CallbackInfo ci;

    public FabricPlayerMessageEvent(PlayerEntity player, String message, CallbackInfo ci) {
        super(player);
        this.message = message;
        this.ci = ci;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isCancelled() {
        return ci.isCancelled();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCancelled(boolean cancelled) {
        if (cancelled) {
            ci.cancel();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<AbstractPlayer> recipients() {
        return new HashSet<>();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setRecipients(Set<AbstractPlayer> recipients) {}
}
