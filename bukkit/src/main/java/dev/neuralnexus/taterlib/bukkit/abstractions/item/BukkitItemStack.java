package dev.neuralnexus.taterlib.bukkit.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import org.bukkit.inventory.ItemStack;

public class BukkitItemStack implements AbstractItemStack {
    private final ItemStack itemStack;

    public BukkitItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public AbstractItemMeta getMeta() {
        return new BukkitItemMeta(itemStack.getItemMeta());
    }

    @Override
    public void setMeta(AbstractItemMeta abstractItemMeta) {
        itemStack.setItemMeta(((BukkitItemMeta) abstractItemMeta).getItemMeta());
    }

    @Override
    public String getType() {
        return itemStack.getType().name();
    }

    @Override
    public int getCount() {
        return itemStack.getAmount();
    }

    @Override
    public void setCount(int i) {

    }
}
