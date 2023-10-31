package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.common.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.inventory.AbstractItemStack;
import org.spongepowered.api.item.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstracts a Sponge inventory to an AbstractInventory.
 */
public class SpongeInventory implements AbstractInventory {
    private final Inventory inventory;

    /**
     * Constructor.
     * @param inventory The Sponge inventory.
     */
    public SpongeInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getSize() {
        return inventory.capacity();
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(int slot) {
        if (!inventory.slot(slot).isPresent()) {
            return null;
        }
        return new SpongeItemStack(inventory.slot(slot).get().peek());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(int slot, AbstractItemStack item) {
        inventory.slot(slot).get().set(((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addItem(AbstractItemStack item) {
        inventory.offer(((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void removeItem(AbstractItemStack item) {
        if (inventory.contains(((SpongeItemStack) item).getItemStack())) {
            for (int i = 0; i < getSize(); i++) {
                if (getItem(i).equals(item)) {
                    setItem(i, null);
                    break;
                }
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getContents() {
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
    public void setContents(AbstractItemStack[] items) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, items[i]);
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
    public void setStorageContents(AbstractItemStack[] items) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, items[i]);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(AbstractItemStack item) {
        return inventory.contains(((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean contains(String type) {
        for (AbstractItemStack item : getContents()) {
            if (item.getType().equals(type)) {
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
    public boolean containsAtLeast(String type, int amount) {
        int total = 0;
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                total += getItem(i).getCount();
            }
        }
        return total >= amount;
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
                inventory.slot(i).get().clear();
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
                inventory.slot(i).get().clear();
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
        inventory.slot(slot).get().clear();
    }
}
