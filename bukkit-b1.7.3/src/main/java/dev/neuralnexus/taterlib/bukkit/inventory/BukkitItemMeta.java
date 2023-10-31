package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractItemMeta;
//import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Abstracts a Bukkit item meta to an AbstractItemMeta.
 */
public class BukkitItemMeta implements AbstractItemMeta {
    // TODO: Refactor into AbstractItem class
    private final Object itemMeta;

    /**
     * Constructor.
     * @param itemMeta The Bukkit item meta.
     */
    public BukkitItemMeta(Object itemMeta) {
        this.itemMeta = itemMeta;
    }

    /**
     * Getter for the Bukkit item meta.
     * @return The Bukkit item meta.
     */
    public Object getItemMeta() {
        return itemMeta;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasDisplayName() {
//        return itemMeta.hasDisplayName();
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
//        return itemMeta.getDisplayName();
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDisplayName(String name) {
//        itemMeta.setDisplayName(name);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasLore() {
//        return itemMeta.hasLore();
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getLore() {
//        return itemMeta.getLore();
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLore(List<String> lore) {
//        itemMeta.setLore(lore);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasEnchants() {
//        return itemMeta.hasEnchants();
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isUnbreakable() {
        return false; // itemMeta.isUnbreakable();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
//        itemMeta.setUnbreakable(unbreakable);
    }
}
