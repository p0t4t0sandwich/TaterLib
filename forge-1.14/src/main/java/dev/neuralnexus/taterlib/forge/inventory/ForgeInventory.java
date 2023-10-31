package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import net.minecraft.inventory.IInventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstracts a Forge inventory to an AbstractInventory.
 */
public class ForgeInventory implements AbstractInventory {
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
        return inventory.getSizeInventory();
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(int slot) {
        return new ForgeItemStack(inventory.getStackInSlot(slot));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(int slot, AbstractItemStack item) {
        inventory.setInventorySlotContents(slot, ((ForgeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addItem(AbstractItemStack item) {
        int firstEmpty = firstEmpty();
        if (firstEmpty == -1) return;
        setItem(firstEmpty, item);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeItem(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                inventory.removeStackFromSlot(i);
                return;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getContents() {
        AbstractItemStack[] abstractContents = new AbstractItemStack[getSize()];
        for (int i = 0; i < getSize(); i++) {
            abstractContents[i] = new ForgeItemStack(inventory.getStackInSlot(i));
        }
        return abstractContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setContents(AbstractItemStack[] items) {
        for (int i = 0; i < getSize(); i++) {
            inventory.setInventorySlotContents(i, ((ForgeItemStack) items[i]).getItemStack());
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getStorageContents() {
        // TODO: Implement
        return new AbstractItemStack[0];
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setStorageContents(AbstractItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(AbstractItemStack item) {
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
    public boolean containsAtLeast(AbstractItemStack item, int count) {
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
    public Map<Integer, AbstractItemStack> all(AbstractItemStack item) {
        Map<Integer, AbstractItemStack> map = new HashMap<>();
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
    public int first(AbstractItemStack item) {
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
    public void remove(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                inventory.removeStackFromSlot(i);
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
                inventory.removeStackFromSlot(i);
            }
        }
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
        inventory.removeStackFromSlot(slot);
    }
}
