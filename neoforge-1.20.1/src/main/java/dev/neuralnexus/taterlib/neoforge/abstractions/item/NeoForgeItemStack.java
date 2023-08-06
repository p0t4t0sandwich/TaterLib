package dev.neuralnexus.taterlib.neoforge.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import net.minecraft.world.item.ItemStack;

/**
 * Abstracts a NeoForge item stack to an AbstractItemStack.
 */
public class NeoForgeItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The NeoForge item stack.
     */
    public NeoForgeItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the NeoForge item stack.
     * @return The NeoForge item stack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemMeta getMeta() {
        return new NeoForgeItemMeta(itemStack);
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
        return itemStack.getItem().toString();
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
}
