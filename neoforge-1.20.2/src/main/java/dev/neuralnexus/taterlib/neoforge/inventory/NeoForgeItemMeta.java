package dev.neuralnexus.taterlib.neoforge.inventory;

import dev.neuralnexus.taterlib.common.inventory.ItemMeta;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

/**
 * Abstracts a NeoForge item meta to an AbstractItemMeta.
 */
public class NeoForgeItemMeta implements ItemMeta {
    private final ItemStack itemStack;

    /**
     * Constructor.
     * @param itemStack The NeoForge item stack.
     */
    public NeoForgeItemMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean hasDisplayName() {
        return itemStack.hasCustomHoverName();
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
        itemStack.setHoverName(Component.nullToEmpty(name));
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
        return itemStack.isEnchanted();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isUnbreakable() {
        return itemStack.isDamageableItem();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setUnbreakable(boolean unbreakable) {
        // TODO: Implement
    }
}
