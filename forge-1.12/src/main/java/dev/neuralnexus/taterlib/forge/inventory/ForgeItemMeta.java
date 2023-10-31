package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractItemMeta;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Abstracts a Forge item meta to an AbstractItemMeta.
 */
public class ForgeItemMeta implements AbstractItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Forge item stack.
     */
    public ForgeItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasDisplayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return itemStack.getDisplayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDisplayName(String name) {
        itemStack.setStackDisplayName(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasLore() {
        // TODO: Implement
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getLore() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasEnchants() {
        return itemStack.isItemEnchanted();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isUnbreakable() {
        return itemStack.isItemStackDamageable();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
