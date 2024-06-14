package dev.neuralnexus.taterlib.v1_20.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

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
        this.itemStack =
                itemStack == null ? new org.bukkit.inventory.ItemStack(Material.AIR) : itemStack;
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
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
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
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
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
        ItemMeta meta = itemStack.getItemMeta();
        meta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(meta);
    }
}
