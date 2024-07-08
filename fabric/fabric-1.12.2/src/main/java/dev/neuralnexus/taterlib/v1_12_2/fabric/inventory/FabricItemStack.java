/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_12_2.fabric.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.inventory.ItemStack;

import net.minecraft.item.Items;

import java.util.List;

/** Fabric implementation of {@link ItemStack}. */
public class FabricItemStack implements ItemStack {
    private final net.minecraft.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Fabric item stack.
     */
    public FabricItemStack(net.minecraft.item.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new net.minecraft.item.ItemStack(Items.AIR) : itemStack;
    }

    /**
     * Getter for the Fabric item stack.
     *
     * @return The Fabric item stack.
     */
    public net.minecraft.item.ItemStack itemStack() {
        return itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public String type() {
        String itemName = itemStack.getItem().toString();
        if (!itemName.contains(":")) {
            return new VanillaResourceLocation("minecraft", itemName);
        }
        return new VanillaResourceLocation(itemName);
    }

    /** {@inheritDoc} */
    @Override
    public int count() {
        return itemStack.getCount();
    }

    /** {@inheritDoc} */
    @Override
    public void setCount(int count) {
        itemStack.setCount(count);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack clone() {
        return new FabricItemStack(itemStack.copy());
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomName();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> displayName() {
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
        return !itemStack.getEnchantments().isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
