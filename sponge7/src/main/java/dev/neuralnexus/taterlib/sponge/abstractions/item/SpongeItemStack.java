package dev.neuralnexus.taterlib.sponge.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import org.spongepowered.api.item.inventory.ItemStack;

/**
 * Abstracts a Sponge item stack to an AbstractItemStack.
 */
public class SpongeItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Sponge item stack.
     */
    public SpongeItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Sponge item stack.
     * @return The Sponge item stack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemMeta getMeta() {
        return new SpongeItemMeta(itemStack);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMeta(AbstractItemMeta itemMeta) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getType() {
        // Turn ItemName into item_name
        // TODO: Make this less jank
        String ItemName = itemStack.getType().toString().split("Item")[0];
        for (int i = 0; i < ItemName.length(); i++) {
            if (Character.isUpperCase(ItemName.charAt(i))) {
                ItemName = ItemName.substring(0, i) + "_" + ItemName.substring(i);
                i++;
            }
        }
        return "minecraft:" + ItemName.toLowerCase().substring(1);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCount() {
        return itemStack.getQuantity();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCount(int count) {
        itemStack.setQuantity(count);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack clone() {
        return new SpongeItemStack(itemStack.copy());
    }
}
