package dev.neuralnexus.taterlib.sponge.inventory;

import dev.neuralnexus.taterlib.inventory.ItemStack;
import dev.neuralnexus.taterlib.inventory.PlayerInventory;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;
import org.spongepowered.api.item.inventory.type.CarriedInventory;

/** Abstracts a Sponge player inventory to an AbstractPlayerInventory. */
public class SpongePlayerInventory extends SpongeInventory implements PlayerInventory {
    private final CarriedInventory<Player> playerInventory;

    /**
     * Constructor.
     *
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(CarriedInventory<Player> playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] armorContents = new ItemStack[4];
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
                        armor.peek(EquipmentTypes.HEADWEAR)
                                .ifPresent(
                                        itemStack ->
                                                armorContents[0] = new SpongeItemStack(itemStack));
                        armor.peek(EquipmentTypes.CHESTPLATE)
                                .ifPresent(
                                        itemStack ->
                                                armorContents[1] = new SpongeItemStack(itemStack));
                        armor.peek(EquipmentTypes.LEGGINGS)
                                .ifPresent(
                                        itemStack ->
                                                armorContents[2] = new SpongeItemStack(itemStack));
                        armor.peek(EquipmentTypes.BOOTS)
                                .ifPresent(
                                        itemStack ->
                                                armorContents[3] = new SpongeItemStack(itemStack));
                    }
                });
        return armorContents;
    }

    /** {@inheritDoc} */
    @Override
    public void setArmorContents(ItemStack[] items) {
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
                        armor.set(
                                EquipmentTypes.HEADWEAR,
                                ((SpongeItemStack) items[0]).getItemStack());
                        armor.set(
                                EquipmentTypes.CHESTPLATE,
                                ((SpongeItemStack) items[1]).getItemStack());
                        armor.set(
                                EquipmentTypes.LEGGINGS,
                                ((SpongeItemStack) items[2]).getItemStack());
                        armor.set(
                                EquipmentTypes.BOOTS, ((SpongeItemStack) items[3]).getItemStack());
                    }
                });
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack[] getExtraContents() {
        // TODO: Implement
        return new ItemStack[0];
    }

    /** {@inheritDoc} */
    @Override
    public void setExtraContents(ItemStack[] items) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack[] helmet =
                new org.spongepowered.api.item.inventory.ItemStack[1];
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
                        armor.peek(EquipmentTypes.HEADWEAR)
                                .ifPresent(itemStack -> helmet[0] = itemStack);
                    }
                });
        return helmet[0] == null ? null : new SpongeItemStack(helmet[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setHelmet(ItemStack item) {
        if (item != null) {
            playerInventory.forEach(
                    inventory -> {
                        if (inventory instanceof EquipmentInventory) {
                            EquipmentInventory armor = (EquipmentInventory) inventory;
                            armor.set(
                                    EquipmentTypes.HEADWEAR,
                                    ((SpongeItemStack) item).getItemStack());
                        }
                    });
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack[] chestplate =
                new org.spongepowered.api.item.inventory.ItemStack[1];
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
                        armor.peek(EquipmentTypes.CHESTPLATE)
                                .ifPresent(itemStack -> chestplate[0] = itemStack);
                    }
                });
        return chestplate[0] == null ? null : new SpongeItemStack(chestplate[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setChestplate(ItemStack item) {
        if (item != null) {
            playerInventory.forEach(
                    inventory -> {
                        if (inventory instanceof EquipmentInventory) {
                            EquipmentInventory armor = (EquipmentInventory) inventory;
                            armor.set(
                                    EquipmentTypes.CHESTPLATE,
                                    ((SpongeItemStack) item).getItemStack());
                        }
                    });
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack[] leggings =
                new org.spongepowered.api.item.inventory.ItemStack[1];
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
                        armor.peek(EquipmentTypes.LEGGINGS)
                                .ifPresent(itemStack -> leggings[0] = itemStack);
                    }
                });
        return leggings[0] == null ? null : new SpongeItemStack(leggings[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setLeggings(ItemStack item) {
        if (item != null) {
            playerInventory.forEach(
                    inventory -> {
                        if (inventory instanceof EquipmentInventory) {
                            EquipmentInventory armor = (EquipmentInventory) inventory;
                            armor.set(
                                    EquipmentTypes.LEGGINGS,
                                    ((SpongeItemStack) item).getItemStack());
                        }
                    });
        }
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack[] boots =
                new org.spongepowered.api.item.inventory.ItemStack[1];
        playerInventory.forEach(
                inventory -> {
                    if (inventory instanceof EquipmentInventory) {
                        EquipmentInventory armor = (EquipmentInventory) inventory;
                        armor.peek(EquipmentTypes.BOOTS)
                                .ifPresent(itemStack -> boots[0] = itemStack);
                    }
                });
        return boots[0] == null ? null : new SpongeItemStack(boots[0]);
    }

    /** {@inheritDoc} */
    @Override
    public void setBoots(ItemStack item) {
        if (item != null) {
            playerInventory.forEach(
                    inventory -> {
                        if (inventory instanceof EquipmentInventory) {
                            EquipmentInventory armor = (EquipmentInventory) inventory;
                            armor.set(
                                    EquipmentTypes.BOOTS, ((SpongeItemStack) item).getItemStack());
                        }
                    });
        }
    }

    /** {@inheritDoc} */
    @Override
    public void setItem(String equipmentSlot, ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInMainHand(ItemStack item) {
        // TODO: Implement
    }

    /** {@inheritDoc} */
    @Override
    public ItemStack getItemInOffHand() {
        // TODO: Implement
        return null;
        //        ItemStack offHand =
        // playerInventory.getEquipment().peek(EquipmentTypes.OFF_HAND).orElse(null);
        //        return offHand == null ? null : new SpongeItemStack(offHand);
    }

    /** {@inheritDoc} */
    @Override
    public void setItemInOffHand(ItemStack item) {
        // TODO: Implement
        //        if (item != null) {
        //            playerInventory.getEquipment().set(EquipmentTypes.OFF_HAND, ((SpongeItemStack)
        // item).getItemStack());
        //        }
    }

    /** {@inheritDoc} */
    @Override
    public int getHeldItemSlot() {
        // TODO: Implement
        return 0;
    }
}
