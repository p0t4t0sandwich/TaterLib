package dev.neuralnexus.taterlib.fabric.abstractions.inventory;

import dev.neuralnexus.taterlib.common.abstractions.inventory.AbstractInventory;
import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.fabric.abstractions.item.FabricItemStack;
import net.minecraft.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class FabricInventory implements AbstractInventory {
    private final Inventory inventory;

    public FabricInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public int getSize() {
        return inventory.size();
    }

    @Override
    public AbstractItemStack getItem(int slot) {
        return new FabricItemStack(inventory.getStack(slot));
    }

    @Override
    public void setItem(int slot, AbstractItemStack item) {
        inventory.setStack(slot, ((FabricItemStack) item).getItemStack());
    }

    @Override
    public void addItem(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals("minecraft:air")) {
                setItem(i, item);
                break;
            }
        }
    }

    @Override
    public void removeItem(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            String itemName = getItem(i).getType();
            if (itemName.equals(item.getType())) {
                inventory.removeStack(i);
            }
        }
    }

    @Override
    public AbstractItemStack[] getContents() {
        int size = getSize();
        AbstractItemStack[] contents = new AbstractItemStack[size];
        for (int i = 0; i < size; i++) {
            contents[i] = getItem(i);
        }

        return contents;
    }

    @Override
    public void setContents(AbstractItemStack[] item) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, item[i]);
        }
    }

    @Override
    public AbstractItemStack[] getStorageContents() {
        AbstractItemStack[] contents = new AbstractItemStack[getSize()];
        for (int i = 0; i < getSize(); i++) {
            contents[i] = getItem(i);
        }

        return contents;
    }

    @Override
    public void setStorageContents(AbstractItemStack[] item) {
        for (int i = 0; i < getSize(); i++) {
            setItem(i, item[i]);
        }
    }

    @Override
    public boolean contains(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

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

    @Override
    public int first(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int first(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstEmpty() {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals("minecraft:air")) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(AbstractItemStack item) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(item.getType())) {
                inventory.removeStack(i);
            }
        }
    }

    @Override
    public void remove(String type) {
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i).getType().equals(type)) {
                inventory.removeStack(i);
            }
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < getSize(); i++) {
            inventory.removeStack(i);
        }
    }

    @Override
    public void clear(int i) {
        inventory.removeStack(i);
    }
}
