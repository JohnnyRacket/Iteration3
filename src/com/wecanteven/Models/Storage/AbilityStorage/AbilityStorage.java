package com.wecanteven.Models.Storage.AbilityStorage;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Factories.AbilityFactories.AbilityFactory;
import com.wecanteven.Visitors.AbilityStorageVisitor;

/**
 * Created by simonnea on 4/4/16.
 */
public class AbilityStorage
{
    private Character owner;
    private AbilityEquipment equipment;
    private AbilityInventory inventory;

    public AbilityStorage(Character owner) {
        this.owner = owner;
        equipment = new FourSlotAbilityEquipment(this);
        inventory = new InfiniteAbilityInventory(this);
    }

    public AbilityStorage(Character owner, AbilityEquipment eq, AbilityInventory inv) {
        this.equipment = eq;
        this.inventory = inv;
        this.owner = owner;
    }

    public void initialize() {
        AbilityFactory factory = new AbilityFactory();

        storeAbility(factory.vendBindWounds(owner));
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

    public AbilityEquipment getAbilityEquipment() {
        return this.equipment;
    }

    public AbilityInventory getAbilityInventory() {
        return this.inventory;
    }

    public void accept(AbilityStorageVisitor visitor) {
        visitor.visitAbilityStorage(this);
        equipment.accept(visitor);
        inventory.accept(visitor);
    }
}
