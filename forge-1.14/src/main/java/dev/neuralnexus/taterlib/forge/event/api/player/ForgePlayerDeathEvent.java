package dev.neuralnexus.taterlib.forge.event.api.player;

import dev.neuralnexus.taterlib.common.entity.Entity;
import dev.neuralnexus.taterlib.common.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import dev.neuralnexus.taterlib.common.player.Player;
import dev.neuralnexus.taterlib.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.forge.inventory.ForgeItemStack;
import dev.neuralnexus.taterlib.forge.player.ForgePlayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Forge implementation of {@link PlayerDeathEvent}.
 */
public class ForgePlayerDeathEvent implements PlayerDeathEvent {
    private final LivingDeathEvent event;
    private List<ItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;
    private String deathMessage = "";

    public ForgePlayerDeathEvent(LivingDeathEvent event) {
        this.event = event;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ItemStack> getDrops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.getEntity().captureDrops() == null) {
            return new ArrayList<>();
        }
        return event.getEntity().captureDrops().stream().map(itemEntity -> new ForgeItemStack(itemEntity.getItem())).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearDrops() {
        drops.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDroppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDroppedExp(int exp) {
        this.droppedExp = exp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntity() {
        return new ForgeEntity(event.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return new ForgePlayer((PlayerEntity) event.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDeathMessage() {
        if (!deathMessage.isEmpty()) {
            return deathMessage;
        }
        return event.getSource().getDeathMessage(event.getEntityLiving()).getString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasKeepInventory() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}
