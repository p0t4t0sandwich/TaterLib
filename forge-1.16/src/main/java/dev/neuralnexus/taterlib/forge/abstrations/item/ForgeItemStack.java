package dev.neuralnexus.taterlib.forge.abstrations.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import net.minecraft.item.ItemStack;

/**
 * Abstracts a Forge item stack to an AbstractItemStack.
 */
public class ForgeItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Forge item stack.
     */
    public ForgeItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Forge item stack.
     * @return The Forge item stack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemMeta getMeta() {
        return new ForgeItemMeta(itemStack);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMeta(AbstractItemMeta item) {
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
    public AbstractItemStack clone() {
        return new ForgeItemStack(itemStack.copy());
    }
}
