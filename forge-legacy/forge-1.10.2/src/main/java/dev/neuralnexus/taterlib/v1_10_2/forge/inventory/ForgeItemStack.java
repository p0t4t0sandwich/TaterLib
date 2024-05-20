package dev.neuralnexus.taterlib.v1_10_2.forge.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import java.util.List;

/** Forge implementation of {@link ItemStack}. */
public class ForgeItemStack implements ItemStack {
    private final net.minecraft.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Forge item stack.
     */
    public ForgeItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * Getter for the Forge item stack.
     *
     * @return The Forge item stack.
     */
    public net.minecraft.item.ItemStack itemStack() {
        return itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        String itemName = itemStack.getItem().toString();
        if (!itemName.contains(":")) {
            return "minecraft:" + itemName;
        }
        return itemName;
    }

    /** {@inheritDoc} */
    @Override
    public int count() {
        return itemStack.stackSize;
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(int count) {
        itemStack.stackSize = count;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack clone() {
        return new ForgeItemStack(itemStack.copy());
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return itemStack.getDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.setStackDisplayName(name);
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public List<String> lore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasEnchants() {
        return itemStack.isItemEnchanted();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        return itemStack.isItemStackDamageable();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
