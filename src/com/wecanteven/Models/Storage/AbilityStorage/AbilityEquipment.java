package com.wecanteven.Models.Storage.AbilityStorage;

import com.wecanteven.MenuView.UIObjectCreationVisitors.AbilityViewObjectCreationVisitor;
import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Storage.StorageComponent;
import com.wecanteven.UtilityClasses.Tuple;
import com.wecanteven.Visitors.AbilityStorageVisitor;

import java.util.Iterator;

/**
 * Created by simonnea on 4/17/16.
 */
public abstract class AbilityEquipment extends StorageComponent<AbilityStorage> {
    public AbilityEquipment(AbilityStorage owner) {
        super(owner);
    }

    public boolean equipAbility(Ability ability, int slot) {
        unequipSlot(slot);
        insertIntoSlot(slot, ability);
        getOwner().removeAbility(ability);
        return true;
    }
    public boolean equipAbility(Ability ability) {
        if (isFull())
            return false;
        insertIntoNextEmpty(ability);
        getOwner().removeAbility(ability);
        return true;
    }
    public boolean unequipSlot(int slot) {
        if (isEquipped(slot)) {
            Ability abilityToRemove = removeFromSlot(slot);
            getOwner().storeAbility(abilityToRemove);
            return true;
        }
        return false;
    }
    public boolean unequipAbility(Ability ability) {
        if (isEquipped(ability)) {
            return unequipSlot(getSlot(ability));
        }
        return false;
    }
    public abstract boolean isEquipped(int slot);
    public boolean isEquipped(Ability ability) {
        Iterator<Ability> iter = getIterator();

        while (iter.hasNext()) {
            if(ability == iter.next())
                return true;
        }

        return false;
    }

    protected abstract boolean insertIntoSlot(int slot, Ability ability);
    protected abstract boolean insertIntoNextEmpty(Ability ability);
    protected abstract boolean isFull();
    protected abstract Ability removeFromSlot(int slot);
    protected abstract int getSlot(Ability ability);
    protected abstract Ability getAbility(int slot);

    public abstract Iterator<Ability> getIterator();
    public abstract Iterator<Tuple<Ability, Integer>> getOrderedIterator();

    public void accept(AbilityStorageVisitor visitor) {
        visitor.visitAbilityEquiped(this);
    }
}
