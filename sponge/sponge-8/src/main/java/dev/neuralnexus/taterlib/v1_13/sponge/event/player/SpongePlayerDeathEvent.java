/**
 * Copyright (c) 2025 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_13.sponge.event.player;

import dev.neuralnexus.taterapi.entity.Entity;
import dev.neuralnexus.taterapi.entity.player.Player;
import dev.neuralnexus.taterapi.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;

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

    @Override
    public List<ItemStack> drops() {
        return new ArrayList<>();
    }

    @Override
    public void setDrops(List<ItemStack> drops) {}

    @Override
    public void clearDrops() {}

    @Override
    public int droppedExp() {
        return 0;
    }

    @Override
    public void setDroppedExp(int exp) {}

    @Override
    public Entity entity() {
        return (Entity) event.entity();
    }

    @Override
    public Player player() {
        return (Player) event.entity();
    }

    @Override
    public String deathMessage() {
        if (!this.deathMessage.isEmpty()) {
            return this.deathMessage;
        }
        return ((org.spongepowered.api.entity.living.player.Player) event.entity()).name()
                + " "
                + PlainTextComponentSerializer.plainText().serialize(event.message());
    }

    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    @Override
    public boolean keepInventory() {
        return event.keepInventory();
    }

    @Override
    public void setKeepInventory(boolean keepInventory) {
        event.setKeepInventory(keepInventory);
    }
}
