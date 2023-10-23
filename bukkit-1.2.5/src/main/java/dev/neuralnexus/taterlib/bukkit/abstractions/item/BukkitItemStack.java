package dev.neuralnexus.taterlib.bukkit.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * Abstracts a Bukkit item stack to an AbstractItemStack.
 */
public class BukkitItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Bukkit item stack.
     */
    public BukkitItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Bukkit item stack.
     * @return The Bukkit item stack.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemMeta getMeta() {
        return new BukkitItemMeta(new Object());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setMeta(AbstractItemMeta item) {
//        itemStack.setItemMeta(((BukkitItemMeta) item).getItemMeta());
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getType() {
        return "minecraft:" + itemStack.getType().name().toLowerCase();
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getCount() {
        return itemStack.getAmount();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setCount(int count) {
        itemStack.setAmount(count);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack clone() {
        return new BukkitItemStack(itemStack.clone());
    }
}
