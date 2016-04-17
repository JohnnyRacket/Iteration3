package com.wecanteven.Models.Storage.AbilityStorage;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.UtilityClasses.Tuple;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by simonnea on 4/17/16.
 */
public class FourSlotAbilityEquipment extends AbilityEquipment {
    private int currentCapacity;
    private Ability[] abilities = new Ability[4];

    public FourSlotAbilityEquipment(AbilityStorage owner) {
        super(owner);

        for (int i = 0; i < abilities.length; i++) {
            abilities[i] = null;
        }
    }

    @Override
    public boolean isEquipped(int slot) {
        return abilities[slot-1] != null;
    }

    @Override
    protected boolean insertIntoSlot(int slot, Ability ability) {
        abilities[slot - 1] = ability;
        currentCapacity++;
        return true;
    }

    @Override
    protected boolean insertIntoNextEmpty(Ability ability) {
        for (int i = 1; i < abilities.length+1; i++) {
            if (!isEquipped(i)) {
                return insertIntoSlot(i, ability);
            }
        }
        return false;
    }

    @Override
    protected boolean isFull() {
        return currentCapacity == 4;
    }

    @Override
    protected Ability removeFromSlot(int slot) {
        Ability abilityToReturn = getAbility(slot);
        abilities[slot - 1] = null;
        return abilityToReturn;
    }

    @Override
    protected int getSlot(Ability ability) {
        for (int i = 0; i < abilities.length; i++) {
            if (abilities[i] == ability) {
                return i+1;
            }
        }
        return -1;
    }

    @Override
    protected Ability getAbility(int slot) {
        if (isEquipped(slot)) {
            return abilities[slot - 1];
        }
        return null;
    }

    @Override
    public Iterator<Ability> getIterator() {
        ArrayList<Ability> abilityList = new ArrayList<>();

        for (int i = 0; i < abilities.length; i++) {
            if (abilities[i] != null) {
                abilityList.add(abilities[i]);
            }
        }

        return abilityList.iterator();
    }

    @Override
    public Iterator<Tuple<Ability, Integer>> getOrderedIterator() {
        ArrayList<Tuple<Ability, Integer>> abilityList = new ArrayList<>();

        for (int i = 0; i < abilities.length; i++) {
            if (abilities[i] != null) {
                abilityList.add(new Tuple<>(abilities[i], i+1));
            }
        }
        return abilityList.iterator();
    }
}
