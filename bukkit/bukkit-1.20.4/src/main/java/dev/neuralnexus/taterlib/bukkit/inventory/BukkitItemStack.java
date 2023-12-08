package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.ItemMeta;
import dev.neuralnexus.taterlib.inventory.ItemStack;

/** Abstracts a Bukkit item stack to an AbstractItemStack. */
public class BukkitItemStack implements ItemStack {
    private final org.bukkit.inventory.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Bukkit item stack.
     */
    public BukkitItemStack(org.bukkit.inventory.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Bukkit item stack.
     *
     * @return The Bukkit item stack.
     */
    public org.bukkit.inventory.ItemStack getItemStack() {
        return itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public ItemMeta getMeta() {
        return new BukkitItemMeta(itemStack.getItemMeta());
    }

    /** {@inheritDoc} */
    @Override
    public void setMeta(ItemMeta item) {
        itemStack.setItemMeta(((BukkitItemMeta) item).getItemMeta());
    }

    /** {@inheritDoc} */
    @Override
    public String getType() {
        return "minecraft:" + itemStack.getType().name().toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public int getCount() {
        return itemStack.getAmount();
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(int count) {
        itemStack.setAmount(count);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack clone() {
        return new BukkitItemStack(itemStack.clone());
    }
}
