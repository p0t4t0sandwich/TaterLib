package dev.neuralnexus.taterlib.forge.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import net.minecraft.inventory.IInventory;

import java.util.HashMap;
import java.util.Map;

/** Forge implementation of {@link Inventory}. */
public class ForgeInventory implements Inventory {
    private final IInventory inventory;

    /**
     * Constructor.
     *
     * @param inventory The Forge inventory.
     */
    public ForgeInventory(IInventory inventory) {
        this.inventory = inventory;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return inventory.getContainerSize();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack item(int slot) {
        return new ForgeItemStack(inventory.getItem(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, ((ForgeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void addItem(ItemStack item) {
        int firstEmpty = firstEmpty();
        if (firstEmpty == -1) return;
        setItem(firstEmpty, item);
    }

    /** {@inheritDoc} */
    @Override
    public void removeItem(ItemStack item) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(item.type())) {
                inventory.removeItemNoUpdate(i);
                return;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] contents() {
        ItemStack[] abstractContents = new ItemStack[size()];
        for (int i = 0; i < size(); i++) {
            abstractContents[i] = new ForgeItemStack(inventory.getItem(i));
        }
        return abstractContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(ItemStack[] items) {
        for (int i = 0; i < size(); i++) {
            inventory.setItem(i, ((ForgeItemStack) items[i]).itemStack());
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] storageContents() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setStorageContents(ItemStack[] items) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(ItemStack item) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(item.type())) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(String type) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAtLeast(ItemStack item, int count) {
        int total = 0;
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(item.type())) {
                total += item(i).count();
            }
        }
        return total >= count;
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAtLeast(String type, int count) {
        int total = 0;
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(type)) {
                total += item(i).count();
            }
        }
        return total >= count;
    }

    /** {@inheritDoc} */
    @Override
    public Map<Integer, ItemStack> all(ItemStack item) {
        Map<Integer, ItemStack> map = new HashMap<>();
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(item.type())) {
                map.put(i, item(i));
            }
        }
        return map;
    }

    /** {@inheritDoc} */
    @Override
    public int first(ItemStack item) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(item.type())) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public int first(String type) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(type)) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public int firstEmpty() {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals("minecraft:air")) {
                return i;
            }
        }
        return -1;
    }

    /** {@inheritDoc} */
    @Override
    public void remove(ItemStack item) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(item.type())) {
                inventory.removeItemNoUpdate(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        for (int i = 0; i < size(); i++) {
            if (item(i).type().equals(type)) {
                inventory.removeItemNoUpdate(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        inventory.clearContent();
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.removeItemNoUpdate(slot);
    }
}
