package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemMeta;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;

/**
 * Abstracts a Sponge item stack to an AbstractItemStack.
 */
public class SpongeItemStack implements ItemStack {
    private final org.spongepowered.api.item.inventory.ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemStack(org.spongepowered.api.item.inventory.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Sponge item stack.
     * @return The Sponge item stack.
     */
    public org.spongepowered.api.item.inventory.ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemMeta getMeta() {
        return new SpongeItemMeta(itemStack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMeta(ItemMeta itemMeta) {
        // TODO: Implement
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return itemStack.getItem().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return itemStack.getQuantity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCount(int count) {
        itemStack.setQuantity(count);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack clone() {
        return new SpongeItemStack(itemStack.copy());
    }
}
