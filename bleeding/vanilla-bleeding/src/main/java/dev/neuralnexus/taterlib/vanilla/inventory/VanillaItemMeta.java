package dev.neuralnexus.taterlib.vanilla.inventory;

import dev.neuralnexus.taterlib.inventory.ItemMeta;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/** The Vanilla implementation of {@link ItemMeta} */
public class VanillaItemMeta implements ItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The Forge item stack.
     */
    public VanillaItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomHoverName();
    }

    /** {@inheritDoc} */
    @Override
    public String getDisplayName() {
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
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public List<String> getLore() {
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
        return itemStack.isEnchanted();
    }

    /** {@inheritDoc} */
    @Override
    public boolean isUnbreakable() {
        return itemStack.isDamageableItem();
    }

    /** {@inheritDoc} */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
