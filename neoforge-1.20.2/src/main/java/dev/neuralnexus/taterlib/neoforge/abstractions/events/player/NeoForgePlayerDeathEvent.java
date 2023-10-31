package dev.neuralnexus.taterlib.neoforge.abstractions.events.player;

import dev.neuralnexus.taterlib.common.abstractions.entity.AbstractEntity;
import dev.neuralnexus.taterlib.common.abstractions.events.player.AbstractPlayerDeathEvent;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayer;
import dev.neuralnexus.taterlib.neoforge.abstractions.entity.NeoForgeEntity;
import dev.neuralnexus.taterlib.neoforge.abstractions.item.NeoForgeItemStack;
import dev.neuralnexus.taterlib.neoforge.abstractions.player.NeoForgePlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NeoForge implementation of {@link AbstractPlayerDeathEvent}.
 */
public class NeoForgePlayerDeathEvent implements AbstractPlayerDeathEvent {
    private final LivingDeathEvent event;
    private List<AbstractItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;
    private String deathMessage = "";

    public NeoForgePlayerDeathEvent(LivingDeathEvent event) {
        this.event = event;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<AbstractItemStack> getDrops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.getEntity().captureDrops() == null) {
            return new ArrayList<>();
        }
        return event.getEntity().captureDrops().stream().map(itemEntity -> new NeoForgeItemStack(itemEntity.getItem())).collect(Collectors.toList());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDrops(List<AbstractItemStack> drops) {
        this.drops = drops;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clearDrops() {
        drops.clear();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getDroppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return event.getEntity().shouldDropExperience() ? event.getEntity().getExperienceReward() : 0;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDroppedExp(int exp) {
        this.droppedExp = exp;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractEntity getEntity() {
        return new NeoForgeEntity(event.getEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractPlayer getPlayer() {
        return new NeoForgePlayer((Player) event.getEntity());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDeathMessage() {
        if (!deathMessage.isEmpty()) {
            return deathMessage;
        }
        return event.getSource().getLocalizedDeathMessage(event.getEntity()).getString();
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
