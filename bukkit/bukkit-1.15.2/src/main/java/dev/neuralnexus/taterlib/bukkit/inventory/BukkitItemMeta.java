package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.ItemMeta;

import java.util.List;

/** Abstracts a Bukkit item meta to an AbstractItemMeta. */
public class BukkitItemMeta implements ItemMeta {
    private final org.bukkit.inventory.meta.ItemMeta itemMeta;

    /**
     * Constructor.
     *
     * @param itemMeta The Bukkit item meta.
     */
    public BukkitItemMeta(org.bukkit.inventory.meta.ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    /**
     * Getter for the Bukkit item meta.
     *
     * @return The Bukkit item meta.
     */
    public org.bukkit.inventory.meta.ItemMeta getItemMeta() {
        return itemMeta;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemMeta.hasDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return itemMeta.getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemMeta.setDisplayName(name);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLore() {
        return itemMeta.hasLore();
    }

    /** {@inheritDoc} */
    @Override
    public List<String> lore() {
        return itemMeta.getLore();
    }

    /** {@inheritDoc} */
    @Override
    public void setLore(List<String> lore) {
        itemMeta.setLore(lore);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasEnchants() {
        return itemMeta.hasEnchants();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        return itemMeta.isUnbreakable();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
    }
}
