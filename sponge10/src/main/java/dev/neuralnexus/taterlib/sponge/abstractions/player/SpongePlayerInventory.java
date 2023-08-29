package dev.neuralnexus.taterlib.sponge.abstractions.player;

import dev.neuralnexus.taterlib.common.abstractions.item.AbstractItemStack;
import dev.neuralnexus.taterlib.common.abstractions.player.AbstractPlayerInventory;
import dev.neuralnexus.taterlib.sponge.abstractions.inventory.SpongeInventory;
import dev.neuralnexus.taterlib.sponge.abstractions.item.SpongeItemStack;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentInventory;
import org.spongepowered.api.item.inventory.equipment.EquipmentType;
import org.spongepowered.api.item.inventory.equipment.EquipmentTypes;

/**
 * Abstracts a Sponge player inventory to an AbstractPlayerInventory.
 */
public class SpongePlayerInventory extends SpongeInventory implements AbstractPlayerInventory {
    private final PlayerInventory playerInventory;

    /**
     * Constructor.
     * @param playerInventory The Sponge player inventory.
     */
    public SpongePlayerInventory(PlayerInventory playerInventory) {
        super(playerInventory);
        this.playerInventory = playerInventory;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getArmorContents() {
        EquipmentInventory armor = playerInventory.armor();
        AbstractItemStack[] armorContents = new AbstractItemStack[4];
        armor.peek(EquipmentTypes.HEAD).ifPresent(itemStack -> armorContents[0] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.CHEST).ifPresent(itemStack -> armorContents[1] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.LEGS).ifPresent(itemStack -> armorContents[2] = new SpongeItemStack(itemStack));
        armor.peek(EquipmentTypes.FEET).ifPresent(itemStack -> armorContents[3] = new SpongeItemStack(itemStack));
        return armorContents;
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack[] getExtraContents() {
        // TODO: Implement
        return new AbstractItemStack[0];
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getHelmet() {
        ItemStack helmet = playerInventory.armor().peek(EquipmentTypes.HEAD).orElse(null);
        return helmet == null ? null : new SpongeItemStack(helmet);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getChestplate() {
        ItemStack chestplate = playerInventory.armor().peek(EquipmentTypes.CHEST).orElse(null);
        return chestplate == null ? null : new SpongeItemStack(chestplate);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getLeggings() {
        ItemStack leggings = playerInventory.armor().peek(EquipmentTypes.LEGS).orElse(null);
        return leggings == null ? null : new SpongeItemStack(leggings);
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getBoots() {
        ItemStack boots = playerInventory.armor().peek(EquipmentTypes.FEET).orElse(null);
        return boots == null ? null : new SpongeItemStack(boots);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItem(String equipmentSlot, AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItem(String equipmentSlot) {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setArmorContents(AbstractItemStack[] items) {
        EquipmentInventory armor = playerInventory.armor();
        armor.set(EquipmentTypes.HEAD, items[0] == null ? null : ((SpongeItemStack) items[0]).getItemStack());
        armor.set(EquipmentTypes.CHEST, items[1] == null ? null : ((SpongeItemStack) items[1]).getItemStack());
        armor.set(EquipmentTypes.LEGS, items[2] == null ? null : ((SpongeItemStack) items[2]).getItemStack());
        armor.set(EquipmentTypes.FEET, items[3] == null ? null : ((SpongeItemStack) items[3]).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setExtraContents(AbstractItemStack[] items) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setHelmet(AbstractItemStack item) {
        playerInventory.armor().set(EquipmentTypes.HEAD, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setChestplate(AbstractItemStack item) {
        playerInventory.armor().set(EquipmentTypes.CHEST, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setLeggings(AbstractItemStack item) {
        playerInventory.armor().set(EquipmentTypes.LEGS, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setBoots(AbstractItemStack item) {
        playerInventory.armor().set(EquipmentTypes.FEET, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInMainHand() {
        // TODO: Implement
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInMainHand(AbstractItemStack item) {
        // TODO: Implement
    }

    /**
     * @inheritDoc
     */
    @Override
    public AbstractItemStack getItemInOffHand() {
        ItemStack offHand = playerInventory.armor().peek(EquipmentTypes.OFF_HAND).orElse(null);
        return offHand == null ? null : new SpongeItemStack(offHand);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setItemInOffHand(AbstractItemStack item) {
        playerInventory.armor().set(EquipmentTypes.OFF_HAND, item == null ? null : ((SpongeItemStack) item).getItemStack());
    }

    /**
     * @inheritDoc
     */
    @Override
    public int getHeldItemSlot() {
        // TODO: Implement
        return 0;
    }
}
