package dev.neuralnexus.taterlib.fabric.inventory;

import dev.neuralnexus.taterlib.inventory.ItemMeta;

import net.minecraft.item.ItemStack;

import java.util.List;

/** Abstracts a Fabric item meta to an AbstractItemMeta. */
public class FabricItemMeta implements ItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Fabric item stack.
     */
    public FabricItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return itemStack.getCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.setCustomName(name);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLore() {
        // TODO: Implement
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public List<String> lore() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasEnchants() {
        return !itemStack.getEnchantments().isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        // TODO: Implement
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
