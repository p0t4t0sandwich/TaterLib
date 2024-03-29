package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/** Fabric implementation of {@link Inventory}. */
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
    public int size() {
        return inventory.size();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack get(int slot) {
        return new FabricItemStack(inventory.getStack(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void set(int slot, ItemStack item) {
        inventory.setStack(slot, ((FabricItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void add(ItemStack item) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals("minecraft:air")) {
                set(i, item);
                break;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(size());
        for (int i = 0; i < size(); i++) {
            contents.add(get(i));
        }
        return contents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(List<ItemStack> contents) {
        for (int i = 0; i < contents.size(); i++) {
            set(i, contents.get(i));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals(type)) {
                inventory.removeStack(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.removeStack(slot);
    }
}
