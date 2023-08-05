package dev.neuralnexus.taterlib.common.abstractions.inventory;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;

import java.util.Map;

public interface AbstractInventory {
    int getSize();
    AbstractItemStack getItem(int slot);
    void setItem(int slot, AbstractItemStack item);
    void addItem(AbstractItemStack item);
    void removeItem(AbstractItemStack item);
    AbstractItemStack[] getContents();
    void setContents(AbstractItemStack[] contents);
    AbstractItemStack[] getStorageContents();
    void setStorageContents(AbstractItemStack[] contents);
    boolean contains(AbstractItemStack item);
    boolean contains(String type);
    boolean containsAtLeast(AbstractItemStack item, int amount);
    boolean containsAtLeast(String type, int amount);
    Map<Integer, AbstractItemStack> all(AbstractItemStack item);
    int first(AbstractItemStack item);
    int first(String type);
    int firstEmpty();
    void remove(AbstractItemStack item);
    void remove(String type);
    void clear();
    void clear(int slot);
}
