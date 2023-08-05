package dev.neuralnexus.taterlib.bukkit.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BukkitItemMeta implements AbstractItemMeta {
    private final ItemMeta itemMeta;

    public BukkitItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    @Override
    public boolean hasDisplayName() {
        return itemMeta.hasDisplayName();
    }

    @Override
    public String getDisplayName() {
        return itemMeta.getDisplayName();
    }

    @Override
    public void setDisplayName(String s) {
        itemMeta.setDisplayName(s);
    }

    @Override
    public boolean hasLore() {
        return itemMeta.hasLore();
    }

    @Override
    public List<String> getLore() {
        return itemMeta.getLore();
    }

    @Override
    public void setLore(List<String> list) {
        itemMeta.setLore(list);
    }

    @Override
    public boolean hasEnchants() {
        return itemMeta.hasEnchants();
    }

    @Override
    public boolean isUnbreakable() {
        return itemMeta.isUnbreakable();
    }

    @Override
    public void setUnbreakable(boolean b) {
        itemMeta.setUnbreakable(b);
    }
}
