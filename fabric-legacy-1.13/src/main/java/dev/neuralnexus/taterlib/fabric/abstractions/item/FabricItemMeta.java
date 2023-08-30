package dev.neuralnexus.taterlib.fabric.abstractions.item;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemMeta;
import net.minecraft.item.ItemStack;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.List;

/**
 * Abstracts a Fabric item meta to an AbstractItemMeta.
 */
public class FabricItemMeta implements AbstractItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The Fabric item stack.
     */
    public FabricItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasDisplayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return itemStack.getDisplayName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDisplayName(String name) {
        itemStack.setDisplayName(new TranslatableComponent(name));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasLore() {
        // TODO: Implement
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<String> getLore() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLore(List<String> lore) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasEnchants() {
        return !itemStack.getEnchantmentList().isEmpty();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isUnbreakable() {
        // TODO: Implement
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
