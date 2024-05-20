package dev.neuralnexus.taterlib.v1_19_2.forge.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import net.minecraft.world.entity.player.Inventory;

import java.util.List;
import java.util.stream.Collectors;

/** Forge implementation of {@link PlayerInventory}. */
public class ForgePlayerInventory extends ForgeInventory implements PlayerInventory {
    private final Inventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Forge player inventory.
     */
    public ForgePlayerInventory(Inventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> armor() {
        return playerInventory.armor.stream().map(ForgeItemStack::new).collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setArmor(List<ItemStack> armor) {
        playerInventory.armor.clear();
        armor.stream()
                .map(ForgeItemStack.class::cast)
                .map(ForgeItemStack::itemStack)
                .forEach(playerInventory.armor::add);
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack offhand() {
        return new ForgeItemStack(playerInventory.offhand.get(0));
    }

    /** {@inheritDoc} */
    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory.offhand.clear();
        playerInventory.offhand.add(((ForgeItemStack) offhand).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int selectedSlot() {
        return playerInventory.selected;
    }
}
