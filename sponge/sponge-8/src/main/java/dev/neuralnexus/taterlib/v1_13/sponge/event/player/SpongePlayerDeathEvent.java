package dev.neuralnexus.taterlib.v1_13.sponge.event.player;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.player.Player;
import dev.neuralnexus.taterlib.v1_13.sponge.entity.SpongeEntity;
import dev.neuralnexus.taterlib.v1_13.sponge.player.SpongePlayer;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import org.spongepowered.api.event.entity.DestructEntityEvent;

import java.util.ArrayList;
import java.util.List;

/** Sponge implementation of {@link PlayerDeathEvent}. */
public class SpongePlayerDeathEvent implements PlayerDeathEvent {
    private final DestructEntityEvent.Death event;
    private String deathMessage = "";

    public SpongePlayerDeathEvent(DestructEntityEvent.Death event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> drops() {
        return new ArrayList<>();
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {}

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {}

    /** {@inheritDoc} */
    @Override
    public int droppedExp() {
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {}

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        return new SpongeEntity(event.entity());
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new SpongePlayer((org.spongepowered.api.entity.living.player.Player) event.entity());
    }

    /** {@inheritDoc} */
    @Override
    public String deathMessage() {
        if (!this.deathMessage.isEmpty()) {
            return this.deathMessage;
        }
        return ((org.spongepowered.api.entity.living.player.Player) event.entity()).name()
                + " "
                + PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    /** {@inheritDoc} */
    @Override
    public boolean keepInventory() {
        return event.keepInventory();
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepInventory(boolean keepInventory) {
        event.setKeepInventory(keepInventory);
    }
}
