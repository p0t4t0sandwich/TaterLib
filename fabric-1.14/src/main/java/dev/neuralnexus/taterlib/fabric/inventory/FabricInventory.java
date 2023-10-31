package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import net.minecraft.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstracts a Fabric inventory to an AbstractInventory.
 */
public class FabricInventory implements AbstractInventory {
    private final Inventory inventory;

    /**
     * Constructor.
     * @param inventory The Fabric inventory.
     */
    public FabricInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getSize() {
        return inventory.getInvSize();
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(int slot) {
        return new FabricItemStack(inventory.getInvStack(slot));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(int slot, AbstractItemStack item) {
        inventory.setInvStack(slot, ((FabricItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addItem(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals("minecraft:air")) {
                setItem(i, item);
                break;
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeItem(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            String itemName = getItem(i).getType();
            if (itemName.equals(item.getType())) {
                inventory.removeInvStack(i);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getContents() {
        int size = getSize();
        AbstractItemStack[] contents = new AbstractItemStack[size];
        for (int i = 0; i < size; i++) {
            contents[i] = getItem(i);
        }

        return contents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setContents(AbstractItemStack[] item) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, item[i]);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getStorageContents() {
        AbstractItemStack[] contents = new AbstractItemStack[getSize()];
        for (int i = 0; i < getSize(); i++) {
            contents[i] = getItem(i);
        }

        return contents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setStorageContents(AbstractItemStack[] item) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, item[i]);
        }
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
    public boolean containsAtLeast(AbstractItemStack item, int amount) {
        int total = 0;
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                total += getItem(i).getCount();
            }
        }
        return total >= amount;
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
                inventory.removeInvStack(i);
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
                inventory.removeInvStack(i);
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clear() {
        for (int i = 0; i < getSize(); i++) {
            inventory.removeInvStack(i);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void clear(int slot) {
        inventory.removeInvStack(slot);
    }
}
