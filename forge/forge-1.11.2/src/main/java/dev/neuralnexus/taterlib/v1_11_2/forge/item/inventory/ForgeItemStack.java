/**
 * Copyright (c) 2024 Dylan Sperrer - dylan@sperrer.ca
 * The project is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE">GPL-3</a>
 * The API is Licensed under <a href="https://github.com/p0t4t0sandwich/TaterLib/blob/dev/LICENSE-API">MIT</a>
 */
package dev.neuralnexus.taterlib.v1_11_2.forge.item.inventory;

import dev.neuralnexus.taterapi.Wrapped;
import dev.neuralnexus.taterapi.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterapi.item.inventory.ItemStack;
import dev.neuralnexus.taterapi.resource.ResourceKey;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Forge implementation of {@link ItemStack}. */
public class ForgeItemStack implements ItemStack, Wrapped<net.minecraft.item.ItemStack> {
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

    @Override
    public net.minecraft.item.ItemStack unwrap() {
        return this.itemStack;
    }

    @Override
    public ResourceKey type() {
        return (ResourceKey) GameRegistry.findRegistry(Item.class).getKey(this.itemStack.getItem());
    }

    @Override
    public int count() {
        return this.itemStack.getCount();
    }

    @Override
    public void setCount(int count) {
        this.itemStack.setCount(count);
    }

    @Override
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    public ItemStack clone() {
        return new ForgeItemStack(this.itemStack.copy());
    }

    @Override
    public boolean hasDisplayName() {
        return this.itemStack.hasDisplayName();
    }

    @Override
    public Optional<String> displayName() {
        return Optional.of(this.itemStack.getDisplayName());
    }

    @Override
    public void setDisplayName(String name) {
        this.itemStack.setStackDisplayName(name);
    }

    @Override
    public boolean hasLore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public List<String> lore() {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }

    @Override
    public boolean hasEnchants() {
        return this.itemStack.isItemEnchanted();
    }

    @Override
    public boolean unbreakable() {
        Objects.requireNonNull(this.itemStack.getTagCompound());
        return this.itemStack.hasTagCompound()
                && this.itemStack.getTagCompound().getBoolean("Unbreakable");
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.itemStack.getOrCreateSubCompound("Unbreakable").setBoolean("Unbreakable", unbreakable);
    }
}
