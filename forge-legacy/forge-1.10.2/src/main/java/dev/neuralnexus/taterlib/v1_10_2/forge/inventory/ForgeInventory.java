package dev.neuralnexus.taterlib.v1_10_2.forge.inventory;

import dev.neuralnexus.taterlib.inventory.Inventory;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import net.minecraft.inventory.IInventory;

import java.util.ArrayList;
import java.util.List;

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
        return inventory.getSizeInventory();
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack get(int slot) {
        return new ForgeItemStack(inventory.getStackInSlot(slot));
    }

    /** {@inheritDoc} */
    @Override
    public void set(int slot, ItemStack item) {
        inventory.setInventorySlotContents(slot, ((ForgeItemStack) item).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public void add(ItemStack item) {
        int firstEmpty = -1;
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals("minecraft:air")) {
                firstEmpty = i;
            }
        }
        if (firstEmpty == -1) return;
        set(firstEmpty, item);
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> contents() {
        List<ItemStack> contents = new ArrayList<>(size());
        for (int i = 0; i < size(); i++) {
            contents.set(i, get(i));
        }
        return contents;
    }

    /** {@inheritDoc} */
    @Override
    public void setContents(List<ItemStack> items) {
        for (int i = 0; i < size(); i++) {
            set(i, items.get(i));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void remove(String type) {
        for (int i = 0; i < size(); i++) {
            if (get(i).type().equals(type)) {
                inventory.removeStackFromSlot(i);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear(int slot) {
        inventory.removeStackFromSlot(slot);
    }
}
