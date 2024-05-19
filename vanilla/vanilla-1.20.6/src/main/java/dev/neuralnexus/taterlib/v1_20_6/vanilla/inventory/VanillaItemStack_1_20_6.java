package dev.neuralnexus.taterlib.v1_20_6.vanilla.inventory;

import dev.neuralnexus.taterlib.v1_20.vanilla.inventory.VanillaItemStack;
import dev.neuralnexus.taterlib.inventory.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;

/** Vanilla implementation of {@link ItemStack}. */
public class VanillaItemStack_1_20_6 extends VanillaItemStack {
    private final net.minecraft.world.item.ItemStack itemStack;

    /**
     * Constructor.
     *
     * @param itemStack The item stack.
     */
    public VanillaItemStack_1_20_6(net.minecraft.world.item.ItemStack itemStack) {
        super(itemStack);
        this.itemStack = itemStack;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasDisplayName() {
        return itemStack.get(DataComponents.CUSTOM_NAME) != null;
    }

    /** {@inheritDoc} */
    @Override
    public void setDisplayName(String name) {
        itemStack.set(DataComponents.CUSTOM_NAME, Component.nullToEmpty(name));
    }
}
