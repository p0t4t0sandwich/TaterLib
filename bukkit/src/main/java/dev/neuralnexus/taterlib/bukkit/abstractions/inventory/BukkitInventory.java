package dev.neuralnexus.taterlib.bukkit.abstractions.inventory;

import dev.neuralnexus.taterlib.bukkit.abstractions.item.BukkitItemStack;
import dev.neuralnexus.taterlib.common.abstractions.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BukkitInventory implements AbstractInventory {
    private final Inventory inventory;

    public BukkitInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public int getSize() {
        return inventory.getSize();
    }

    @Override
    public AbstractItemStack getItem(int i) {
        return inventory.getItem(i) == null ? null : new BukkitItemStack(inventory.getItem(i));
    }

    @Override
    public void setItem(int i, AbstractItemStack abstractItemStack) {
        inventory.setItem(i, ((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public void addItem(AbstractItemStack abstractItemStack) {
        inventory.addItem(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public void removeItem(AbstractItemStack abstractItemStack) {
        inventory.removeItem(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public AbstractItemStack[] getContents() {
        ItemStack[] contents = inventory.getContents();
        AbstractItemStack[] abstractContents = new AbstractItemStack[contents.length];
        for (int i = 0; i < contents.length; i++) {
            abstractContents[i] = contents[i] == null ? null : new BukkitItemStack(contents[i]);
        }

        return abstractContents;
    }

    @Override
    public void setContents(AbstractItemStack[] abstractItemStacks) {
        ItemStack[] contents = new ItemStack[abstractItemStacks.length];
        for (int i = 0; i < abstractItemStacks.length; i++) {
            contents[i] = ((BukkitItemStack) abstractItemStacks[i]).getItemStack();
        }

        inventory.setContents(contents);
    }

    @Override
    public AbstractItemStack[] getStorageContents() {
        ItemStack[] storageContents = inventory.getStorageContents();
        AbstractItemStack[] abstractStorageContents = new AbstractItemStack[storageContents.length];
        for (int i = 0; i < storageContents.length; i++) {
            abstractStorageContents[i] = storageContents[i] == null ? null : new BukkitItemStack(storageContents[i]);
        }

        return abstractStorageContents;
    }

    @Override
    public void setStorageContents(AbstractItemStack[] abstractItemStacks) {
        ItemStack[] storageContents = new ItemStack[abstractItemStacks.length];
        for (int i = 0; i < abstractItemStacks.length; i++) {
            storageContents[i] = ((BukkitItemStack) abstractItemStacks[i]).getItemStack();
        }

        inventory.setStorageContents(storageContents);
    }

    @Override
    public boolean contains(AbstractItemStack abstractItemStack) {
        return inventory.contains(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public boolean contains(String s) {
        if (s.contains(":")) {
            s = s.split(":")[1];
        }
        return inventory.contains(Material.valueOf(s.toUpperCase()));
    }

    @Override
    public boolean containsAtLeast(AbstractItemStack abstractItemStack, int i) {
        return inventory.containsAtLeast(((BukkitItemStack) abstractItemStack).getItemStack(), i);
    }

    @Override
    public boolean containsAtLeast(String s, int i) {
        if (s.contains(":")) {
            s = s.split(":")[1];
        }
        return inventory.containsAtLeast(new ItemStack(Material.valueOf(s.toUpperCase())), i);
    }

    @Override
    public Map<Integer, AbstractItemStack> all(AbstractItemStack abstractItemStack) {
        Map<Integer, ItemStack> all = (Map<Integer, ItemStack>) inventory.all(((BukkitItemStack) abstractItemStack).getItemStack());
        Map<Integer, AbstractItemStack> abstractAll = new HashMap<>();
        for (Map.Entry<Integer, ItemStack> entry : all.entrySet()) {
            abstractAll.put(entry.getKey(), entry.getValue() == null ? null : new BukkitItemStack(entry.getValue()));
        }
        return abstractAll;
    }

    @Override
    public int first(AbstractItemStack abstractItemStack) {
        return inventory.first(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public int first(String s) {
        if (s.contains(":")) {
            s = s.split(":")[1];
        }
        return inventory.first(Material.valueOf(s.toUpperCase()));
    }

    @Override
    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    @Override
    public void remove(AbstractItemStack abstractItemStack) {
        inventory.remove(((BukkitItemStack) abstractItemStack).getItemStack());
    }

    @Override
    public void remove(String s) {
        if (s.contains(":")) {
            s = s.split(":")[1];
        }
        inventory.remove(Material.valueOf(s.toUpperCase()));
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    public void clear(int i) {
        inventory.clear(i);
    }
}
