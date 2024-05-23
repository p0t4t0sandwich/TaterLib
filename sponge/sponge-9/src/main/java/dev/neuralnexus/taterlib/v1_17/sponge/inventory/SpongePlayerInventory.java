package dev.neuralnexus.taterlib.v1_17.sponge.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Sponge implementation of {@link PlayerInventory}. */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(
            org.spongepowered.api.item.inventory.entity.PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public List<ItemStack> armor() {
        EquipmentInventory armor = playerInventory.armor();
        List<ItemStack> armorContents = new ArrayList<>(4);
        Optional<org.spongepowered.api.item.inventory.ItemStack> head =
                armor.peek(EquipmentTypes.HEAD);
        armorContents.set(0, head.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> chest =
                armor.peek(EquipmentTypes.CHEST);
        armorContents.set(1, chest.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> legs =
                armor.peek(EquipmentTypes.LEGS);
        armorContents.set(2, legs.map(SpongeItemStack::new).orElse(null));
        Optional<org.spongepowered.api.item.inventory.ItemStack> feet =
                armor.peek(EquipmentTypes.FEET);
        armorContents.set(3, feet.map(SpongeItemStack::new).orElse(null));
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmor(List<ItemStack> armor) {
        armor.stream()
                .map(SpongeItemStack.class::cast)
                .map(SpongeItemStack::itemStack)
                .forEach(itemStack -> playerInventory.armor().offer(itemStack));
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack offhand() {
        Optional<org.spongepowered.api.item.inventory.ItemStack> offhand =
                playerInventory.equipment().peek(EquipmentTypes.OFF_HAND);
        return offhand.map(SpongeItemStack::new).orElse(null);
    }

    /** {@inheritDoc} */
    @Override
    public void setOffhand(ItemStack offhand) {
        playerInventory
                .equipment()
                .set(EquipmentTypes.OFF_HAND, ((SpongeItemStack) offhand).itemStack());
    }

    /** {@inheritDoc} */
    @Override
    public int selectedSlot() {
        return playerInventory.hotbar().selectedSlotIndex();
    }
}