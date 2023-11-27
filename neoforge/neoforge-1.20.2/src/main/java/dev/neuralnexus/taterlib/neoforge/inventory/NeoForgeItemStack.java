package dev.neuralnexus.taterlib.neoforge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemMeta;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;

/**
 * Abstracts a NeoForge item stack to an AbstractItemStack.
 */
public class NeoForgeItemStack implements ItemStack {
    private final net.minecraft.world.item.ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The NeoForge item stack.
     */
    public NeoForgeItemStack(net.minecraft.world.item.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the NeoForge item stack.
     * @return The NeoForge item stack.
     */
    public net.minecraft.world.item.ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemMeta getMeta() {
        return new NeoForgeItemMeta(itemStack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMeta(ItemMeta item) {
        // TODO: Implement
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        String itemName = itemStack.getItem().toString();
        if (!itemName.contains(":")) {
            return "minecraft:" + itemName;
        }
        return itemName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return itemStack.getCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCount(int count) {
        itemStack.setCount(count);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemStack clone() {
        return new NeoForgeItemStack(itemStack.copy());
    }
}
