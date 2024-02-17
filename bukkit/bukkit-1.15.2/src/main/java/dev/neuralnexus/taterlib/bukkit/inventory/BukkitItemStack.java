package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;

import java.util.List;

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
    public org.bukkit.inventory.ItemStack itemStack() {
        return itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        return "minecraft:" + itemStack.getType().name().toLowerCase();
    }

    /** {@inheritDoc} */
    @Override
    public int count() {
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

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.getItemMeta().hasDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return itemStack.getItemMeta().getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.getItemMeta().setDisplayName(name);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLore() {
        return itemStack.getItemMeta().hasLore();
    }

    /** {@inheritDoc} */
    @Override
    public List<String> lore() {
        return itemStack.getItemMeta().getLore();
    }

    /** {@inheritDoc} */
    @Override
    public void setLore(List<String> lore) {
        itemStack.getItemMeta().setLore(lore);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasEnchants() {
        return itemStack.getItemMeta().hasEnchants();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        return itemStack.getItemMeta().isUnbreakable();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemStack.getItemMeta().setUnbreakable(unbreakable);
    }
}
