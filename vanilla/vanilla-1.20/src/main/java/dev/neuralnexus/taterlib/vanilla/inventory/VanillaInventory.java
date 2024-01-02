package dev.neuralnexus.taterlib.vanilla.inventory;

import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/** The Vanilla implementation of {@link Inventory} */
public class VanillaInventory implements Inventory {
    private final net.minecraft.world.entity.player.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Forge inventory.
     */
    public VanillaInventory(net.minecraft.world.entity.player.Inventory inventory) {
        this.inventory = inventory;
    }

    /** {@inheritDoc} */
    @Override
    public int getSize() {
        return inventory.getContainerSize();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(int slot) {
        return new VanillaItemStack(inventory.getItem(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, ((VanillaItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void addItem(ItemStack item) {
        inventory.add(((VanillaItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void removeItem(ItemStack item) {
        inventory.removeItem(((VanillaItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getContents() {
        ItemStack[] abstractContents = new ItemStack[getSize()];
        for (int i = 0; i < getSize(); i++) {
            abstractContents[i] = new VanillaItemStack(inventory.getItem(i));
        }
        return abstractContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(ItemStack[] items) {
        for (int i = 0; i < getSize(); i++) {
            inventory.setItem(i, ((VanillaItemStack) items[i]).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getStorageContents() {
        // TODO: Implement
        return new ItemStack[0];
    }

    /** {@inheritDoc} */
    @Override
    public void setStorageContents(ItemStack[] items) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAtLeast(ItemStack item, int count) {
        int total = 0;
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                total += getItem(i).getCount();
            }
        }
        return total >= count;
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAtLeast(String type, int count) {
        int total = 0;
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                total += getItem(i).getCount();
            }
        }
        return total >= count;
    }

    /** {@inheritDoc} */
    @Override
    public Map<Integer, ItemStack> all(ItemStack item) {
        Map<Integer, ItemStack> map = new HashMap<>();
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                map.put(i, getItem(i));
            }
        }
        return map;
    }

    /** {@inheritDoc} */
    @Override
    public int first(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public int first(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public int firstEmpty() {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals("minecraft:air")) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void remove(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            inventory.removeItem(((VanillaItemStack) item).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                inventory.removeItem(((VanillaItemStack) getItem(i)).getItemStack());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        for (int i = 0; i < getSize(); i++) {
            inventory.removeItem(((VanillaItemStack) getItem(i)).getItemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.removeItem(((VanillaItemStack) getItem(slot)).getItemStack());
    }
}
