package dev.neuralnexus.taterlib.v1_8_9.forge.event.player;

import dev.neuralnexus.taterlib.entity.Entity;
import dev.neuralnexus.taterlib.event.player.PlayerDeathEvent;
import dev.neuralnexus.taterlib.v1_8_9.forge.entity.ForgeEntity;
import dev.neuralnexus.taterlib.v1_8_9.forge.inventory.ForgeItemStack;
import dev.neuralnexus.taterlib.v1_8_9.forge.player.ForgePlayer;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.player.Player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link PlayerDeathEvent}. */
public class ForgePlayerDeathEvent implements PlayerDeathEvent {
    private final LivingDeathEvent event;
    private List<ItemStack> drops = new ArrayList<>();
    private int droppedExp = 0;
    private String deathMessage = "";

    public ForgePlayerDeathEvent(LivingDeathEvent event) {
        this.event = event;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> drops() {
        if (!drops.isEmpty()) {
            return drops;
        }
        if (event.entity.capturedDrops == null) {
            return new ArrayList<>();
        }
        return event.entity.capturedDrops.stream()
                .map(itemEntity -> new ForgeItemStack(itemEntity.getEntityItem()))
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setDrops(List<ItemStack> drops) {
        this.drops = drops;
    }

    /** {@inheritDoc} */
    @Override
    public void clearDrops() {
        drops.clear();
    }

    /** {@inheritDoc} */
    @Override
    public int droppedExp() {
        if (droppedExp != 0) {
            return droppedExp;
        }
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public void setDroppedExp(int exp) {
        this.droppedExp = exp;
    }

    /** {@inheritDoc} */
    @Override
    public Entity entity() {
        return new ForgeEntity(event.entity);
    }

    /** {@inheritDoc} */
    @Override
    public Player player() {
        return new ForgePlayer((EntityPlayer) event.entity);
    }

    /** {@inheritDoc} */
    @Override
    public String deathMessage() {
        if (!deathMessage.isEmpty()) {
            return deathMessage;
        }
        return event.source.getDeathMessage(event.entityLiving).getFormattedText();
    }

    /** {@inheritDoc} */
    @Override
    public void setDeathMessage(String deathMessage) {
        this.deathMessage = deathMessage;
    }

    /** {@inheritDoc} */
    @Override
    public boolean keepInventory() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void setKeepInventory(boolean keepInventory) {}
}