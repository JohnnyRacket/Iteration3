package com.wecanteven.Models.Storage.AbilityStorage;

import com.wecanteven.Models.Abilities.Ability;

/**
 * Created by simonnea on 4/4/16.
 */
public class AbilityStorage
{
    private AbilityEquipment equipment;
    private AbilityInventory inventory;

    public AbilityStorage() {
        equipment = new FourSlotAbilityEquipment(this);
        inventory = new InfiniteAbilityInventory(this);
    }

    public AbilityStorage(AbilityEquipment eq, AbilityInventory inv) {
        this.equipment = eq;
        this.inventory = inv;
    }

    public void equipAbility(Ability ability, int index) {
        if (inventory.containsAbility(ability)) {
            equipment.equipAbility(ability, index);
        }
    }

    public void equipAbility(Ability ability) {
        if (inventory.containsAbility(ability)) {
            equipment.equipAbility(ability);
        }
    }

    public void unequipAbility(Ability ability) {
        equipment.unequipAbility(ability);
    }

    public void unequipAbility(int slot) {
        equipment.unequipSlot(slot);
    }

    public void useAbility(Ability ability) {
        if (equipment.isEquipped(ability)) {
            ability.cast();
        }
    }

    public void useAbility(int index) {
        if (equipment.isEquipped(index)) {
            equipment.getAbility(index).cast();
        }
    }

    public void storeAbility(Ability ability) {
        inventory.addAbility(ability);
    }

    public void removeAbility(Ability ability) { inventory.removeAbilty(ability); }
}
