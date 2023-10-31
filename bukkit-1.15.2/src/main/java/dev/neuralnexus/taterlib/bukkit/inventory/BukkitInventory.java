package dev.neuralnexus.taterlib.bukkit.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstracts a Bukkit inventory to an AbstractInventory.
 */
public class BukkitInventory implements AbstractInventory {
    private final Inventory inventory;

    /**
     * Constructor.
     * @param inventory The Bukkit inventory.
     */
    public BukkitInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getSize() {
        return inventory.getSize();
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(int slot) {
        return inventory.getItem(slot) == null ? null : new BukkitItemStack(inventory.getItem(slot));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(int slot, AbstractItemStack item) {
        inventory.setItem(slot, ((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addItem(AbstractItemStack item) {
        inventory.addItem(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeItem(AbstractItemStack item) {
        inventory.removeItem(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getContents() {
        ItemStack[] contents = inventory.getContents();
        AbstractItemStack[] abstractContents = new AbstractItemStack[contents.length];
        for (int i = 0; i < contents.length; i++) {
            abstractContents[i] = contents[i] == null ? null : new BukkitItemStack(contents[i]);
        }

        return abstractContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setContents(AbstractItemStack[] items) {
        ItemStack[] contents = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            contents[i] = ((BukkitItemStack) items[i]).getItemStack();
        }

        inventory.setContents(contents);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getStorageContents() {
        ItemStack[] storageContents = inventory.getStorageContents();
        AbstractItemStack[] abstractStorageContents = new AbstractItemStack[storageContents.length];
        for (int i = 0; i < storageContents.length; i++) {
            abstractStorageContents[i] = storageContents[i] == null ? null : new BukkitItemStack(storageContents[i]);
        }

        return abstractStorageContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setStorageContents(AbstractItemStack[] items) {
        ItemStack[] storageContents = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            storageContents[i] = ((BukkitItemStack) items[i]).getItemStack();
        }

        inventory.setStorageContents(storageContents);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(AbstractItemStack item) {
        return inventory.contains(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        return inventory.contains(Material.valueOf(type.toUpperCase()));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean containsAtLeast(AbstractItemStack item, int count) {
        return inventory.containsAtLeast(((BukkitItemStack) item).getItemStack(), count);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean containsAtLeast(String type, int count) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        return inventory.containsAtLeast(new ItemStack(Material.valueOf(type.toUpperCase())), count);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Map<Integer, AbstractItemStack> all(AbstractItemStack item) {
        Map<Integer, ItemStack> all = (Map<Integer, ItemStack>) inventory.all(((BukkitItemStack) item).getItemStack());
        Map<Integer, AbstractItemStack> abstractAll = new HashMap<>();
        for (Map.Entry<Integer, ItemStack> entry : all.entrySet()) {
            abstractAll.put(entry.getKey(), entry.getValue() == null ? null : new BukkitItemStack(entry.getValue()));
        }
        return abstractAll;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int first(AbstractItemStack item) {
        return inventory.first(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public int first(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        return inventory.first(Material.valueOf(type.toUpperCase()));
    }

    /**
     * @inheritDoc
     */
    @Override
    public int firstEmpty() {
        return inventory.firstEmpty();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove(AbstractItemStack item) {
        inventory.remove(((BukkitItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove(String type) {
        if (type.contains(":")) {
            type = type.split(":")[1];
        }
        inventory.remove(Material.valueOf(type.toUpperCase()));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clear() {
        inventory.clear();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clear(int slot) {
        inventory.clear(slot);
    }
}
