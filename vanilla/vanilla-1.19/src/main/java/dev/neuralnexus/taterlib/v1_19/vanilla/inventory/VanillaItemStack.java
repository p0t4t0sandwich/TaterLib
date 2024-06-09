package dev.neuralnexus.taterlib.v1_19.vanilla.inventory;

import dev.neuralnexus.taterlib.exceptions.VersionFeatureNotSupportedException;
import dev.neuralnexus.taterlib.inventory.ItemStack;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import java.util.List;

/** Vanilla implementation of {@link ItemStack} */
public class VanillaItemStack implements ItemStack {
    private final net.minecraft.world.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Vanilla item stack.
     */
    public VanillaItemStack(net.minecraft.world.item.ItemStack itemStack) {
        this.itemStack =
                itemStack == null ? new net.minecraft.world.item.ItemStack(Items.AIR) : itemStack;
    }

    /**
     * Getter for the Vanilla item stack.
     *
     * @return The Vanilla item stack.
     */
    public net.minecraft.world.item.ItemStack itemStack() {
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
        return new VanillaItemStack(itemStack.copy());
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomHoverName();
    }

    /** {@inheritDoc} */
    @Override
    public String displayName() {
        return itemStack.getDisplayName().getString();
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.setHoverName(Component.nullToEmpty(name));
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
        return itemStack.isDamageableItem();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
        throw new VersionFeatureNotSupportedException();
    }
}
