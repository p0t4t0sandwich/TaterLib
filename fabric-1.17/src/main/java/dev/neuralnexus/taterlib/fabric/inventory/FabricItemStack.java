package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemMeta;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;

/**
 * Abstracts a Fabric item stack to an AbstractItemStack.
 */
public class FabricItemStack implements ItemStack {
    private final net.minecraft.item.ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Fabric item stack.
     */
    public FabricItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Fabric item stack.
     * @return The Fabric item stack.
     */
    public net.minecraft.item.ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemMeta getMeta() {
        return new FabricItemMeta(itemStack);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMeta(ItemMeta itemMeta) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
     */
    @Override
    public int getCount() {
        return itemStack.getCount();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCount(int count) {
        itemStack.setCount(count);
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack clone() {
        return new FabricItemStack(itemStack.copy());
    }
}
