package dev.neuralnexus.taterlib.sponge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.sponge.abstractions.entity.SpongeEntity;
import dev.neuralnexus.taterlib.sponge.abstractions.player.SpongePlayer;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.entity.DestructEntityEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Sponge implementation of {@link AbstractPlayerDeathEvent}.
 */
public class SpongePlayerDeathEvent implements AbstractPlayerDeathEvent {
    private final DestructEntityEvent.Death event;
    private String deathMessage = "";

    public SpongePlayerDeathEvent(DestructEntityEvent.Death event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<AbstractItemStack> getDrops() {
        return new ArrayList<>();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<AbstractItemStack> drops) {}

    /**
     * @inheritDoc
     */
    @Override
    public void clearDrops() {}

    /**
     * @inheritDoc
     */
    @Override
    public int getDroppedExp() {
        return 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {}

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new SpongeEntity(event.getTargetEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new SpongePlayer((Player) event.getTargetEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDeathMessage() {
        if (!this.deathMessage.isEmpty()) {
            return this.deathMessage;
        }
        return event.getMessage().toPlain();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasKeepInventory() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
