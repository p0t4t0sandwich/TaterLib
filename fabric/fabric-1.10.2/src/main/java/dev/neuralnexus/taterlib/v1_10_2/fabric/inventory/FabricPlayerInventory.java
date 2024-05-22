package dev.neuralnexus.taterlib.v1_10_2.fabric.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** Fabric implementation of {@link PlayerInventory}. */
public class FabricPlayerInventory extends FabricInventory implements PlayerInventory {
    private final net.minecraft.entity.player.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Fabric player inventory.
     */
    public FabricPlayerInventory(net.minecraft.entity.player.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> armor() {
        return Arrays.stream(playerInventory.armor)
                .map(FabricItemStack::new)
                .collect(Collectors.toList());
    }

    /** {@inheritDoc} */
    @Override
    public void setArmor(List<ItemStack> armor) {
        for (int i = 0; i < playerInventory.armor.length; i++) {
            playerInventory.armor[i] = ((FabricItemStack) armor.get(i)).itemStack();
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack offhand() {
        return new FabricItemStack(playerInventory.field_14790[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory.field_14790[0] = ((FabricItemStack) offhand).itemStack();
    }

    /** {@inheritDoc} */
    @Override
    public int selectedSlot() {
        return playerInventory.selectedSlot;
    }
}
