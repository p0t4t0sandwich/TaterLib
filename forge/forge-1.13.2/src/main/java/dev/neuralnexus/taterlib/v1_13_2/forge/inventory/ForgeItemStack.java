/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */

package dev.neuralnexus.taterlib.v1_13_2.forge.inventory;

import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.inventory.ItemStack;

import net.minecraft.init.Items;
import net.minecraft.util.text.TextComponentString;

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
        this.itemStack =
                itemStack == null ? new net.minecraft.item.ItemStack(Items.AIR) : itemStack;
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
        return new ForgeItemStack(itemStack.copy());
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasDisplayName();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<String> displayName() {
        if (!itemStack.hasCustomHoverName()) return Optional.empty();
        return Optional.of(itemStack.getDisplayName().getString());
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.setDisplayName(new TextComponentString(name));
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
        return itemStack.isEnchanted();
    }

    /** {@inheritDoc} */
    @Override
    public boolean unbreakable() {
        return itemStack.isDamageable();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
