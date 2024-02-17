package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

/** Abstracts a Bukkit inventory to an AbstractInventory. */
public class BukkitInventory implements Inventory {
    private final org.bukkit.inventory.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Bukkit inventory.
     */
    public BukkitInventory(org.bukkit.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return inventory.getSize();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack item(int slot) {
        return inventory.getItem(slot) == null
                ? null
                : new BukkitItemStack(inventory.getItem(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, ((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void addItem(ItemStack item) {
        inventory.addItem(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void removeItem(ItemStack item) {
        inventory.removeItem(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] contents() {
        org.bukkit.inventory.ItemStack[] contents = inventory.getContents();
        ItemStack[] abstractContents = new ItemStack[contents.length];
        for (int i = 0; i < contents.length; i++) {
            abstractContents[i] = contents[i] == null ? null : new BukkitItemStack(contents[i]);
        }

        return abstractContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(ItemStack[] items) {
        org.bukkit.inventory.ItemStack[] contents =
                new org.bukkit.inventory.ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            contents[i] = ((BukkitItemStack) items[i]).itemStack();
        }

        inventory.setContents(contents);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] storageContents() {
        org.bukkit.inventory.ItemStack[] storageContents = inventory.getStorageContents();
        ItemStack[] abstractStorageContents = new ItemStack[storageContents.length];
        for (int i = 0; i < storageContents.length; i++) {
            abstractStorageContents[i] =
                    storageContents[i] == null ? null : new BukkitItemStack(storageContents[i]);
        }

        return abstractStorageContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setStorageContents(ItemStack[] items) {
        org.bukkit.inventory.ItemStack[] storageContents =
                new org.bukkit.inventory.ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            storageContents[i] = ((BukkitItemStack) items[i]).itemStack();
        }

        inventory.setStorageContents(storageContents);
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(ItemStack item) {
        return inventory.contains(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        return inventory.contains(Material.valueOf(type.toUpperCase()));
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAtLeast(ItemStack item, int count) {
        return inventory.containsAtLeast(((BukkitItemStack) item).itemStack(), count);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAtLeast(String type, int count) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        return inventory.containsAtLeast(
                new org.bukkit.inventory.ItemStack(Material.valueOf(type.toUpperCase())), count);
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public Map<Integer, ItemStack> all(ItemStack item) {
        Map<Integer, org.bukkit.inventory.ItemStack> all =
                (Map<Integer, org.bukkit.inventory.ItemStack>)
                        inventory.all(((BukkitItemStack) item).itemStack());
        Map<Integer, ItemStack> abstractAll = new HashMap<>();
        for (Map.Entry<Integer, org.bukkit.inventory.ItemStack> entry : all.entrySet()) {
            abstractAll.put(
                    entry.getKey(),
                    entry.getValue() == null ? null : new BukkitItemStack(entry.getValue()));
        }
        return abstractAll;
    }

    /** {@inheritDoc} */
    @Override
    public int first(ItemStack item) {
        return inventory.first(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int first(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        return inventory.first(Material.valueOf(type.toUpperCase()));
    }

    /** {@inheritDoc} */
    @Override
    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public void remove(ItemStack item) {
        inventory.remove(((BukkitItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        inventory.remove(Material.valueOf(type.toUpperCase()));
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        inventory.clear();
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.clear(slot);
    }
}
