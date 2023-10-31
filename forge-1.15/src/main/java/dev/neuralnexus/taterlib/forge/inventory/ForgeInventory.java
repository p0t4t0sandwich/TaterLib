package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.common.inventory.Inventory;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;
import net.minecraft.inventory.IInventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstracts a Forge inventory to an AbstractInventory.
 */
public class ForgeInventory implements Inventory {
    private final IInventory inventory;

    /**
     * Constructor.
     * @param inventory The Forge inventory.
     */
    public ForgeInventory(IInventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getSize() {
        return inventory.getContainerSize();
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack getItem(int slot) {
        return new ForgeItemStack(inventory.getItem(slot));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addItem(ItemStack item) {
        int firstEmpty = firstEmpty();
        if (firstEmpty == -1) return;
        setItem(firstEmpty, item);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeItem(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                inventory.removeItemNoUpdate(i);
                return;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack[] getContents() {
        ItemStack[] abstractContents = new ItemStack[getSize()];
        for (int i = 0; i < getSize(); i++) {
            abstractContents[i] = new ForgeItemStack(inventory.getItem(i));
        }
        return abstractContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setContents(ItemStack[] items) {
        for (int i = 0; i < getSize(); i++) {
            inventory.setItem(i, ((ForgeItemStack) items[i]).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public ItemStack[] getStorageContents() {
        // TODO: Implement
        return new ItemStack[0];
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setStorageContents(ItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @inheritDoc
     */
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

    /**
     * @inheritDoc
     */
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

    /**
     * @inheritDoc
     */
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

    /**
     * @inheritDoc
     */
    @Override
    public int first(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int first(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int firstEmpty() {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals("minecraft:air")) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                inventory.removeItemNoUpdate(i);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void remove(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                inventory.removeItemNoUpdate(i);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clear() {
        inventory.clearContent();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clear(int slot) {
        inventory.removeItemNoUpdate(slot);
    }
}
