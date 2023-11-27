package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.common.inventory.Inventory;
import dev.neuralnexus.taterlib.common.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/** Abstracts a Fabric inventory to an AbstractInventory. */
public class FabricInventory implements Inventory {
    private final net.minecraft.inventory.Inventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Fabric inventory.
     */
    public FabricInventory(net.minecraft.inventory.Inventory inventory) {
        this.inventory = inventory;
    }

    /** {@inheritDoc} */
    @Override
    public int getSize() {
        return inventory.getInvSize();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(int slot) {
        return new FabricItemStack(inventory.getInvStack(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setInvStack(slot, ((FabricItemStack) item).getItemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void addItem(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals("minecraft:air")) {
                setItem(i, item);
                break;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void removeItem(ItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            String itemName = getItem(i).getType();
            if (itemName.equals(item.getType())) {
                inventory.removeInvStack(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getContents() {
        int size = getSize();
        ItemStack[] contents = new ItemStack[size];
        for (int i = 0; i < size; i++) {
            contents[i] = getItem(i);
        }

        return contents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(ItemStack[] item) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, item[i]);
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getStorageContents() {
        ItemStack[] contents = new ItemStack[getSize()];
        for (int i = 0; i < getSize(); i++) {
            contents[i] = getItem(i);
        }

        return contents;
    }

    /** {@inheritDoc} */
    @Override
    public void setStorageContents(ItemStack[] item) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, item[i]);
        }
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
    public boolean containsAtLeast(ItemStack item, int amount) {
        int total = 0;
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                total += getItem(i).getCount();
            }
        }
        return total >= amount;
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
            if (getItem(i).getType().equals(item.getType())) {
                inventory.removeInvStack(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                inventory.removeInvStack(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        for (int i = 0; i < getSize(); i++) {
            inventory.removeInvStack(i);
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.removeInvStack(slot);
    }
}
