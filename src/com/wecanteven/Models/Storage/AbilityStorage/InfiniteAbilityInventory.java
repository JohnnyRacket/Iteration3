package com.wecanteven.Models.Storage.AbilityStorage;

import com.wecanteven.Models.Abilities.Ability;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by simonnea on 4/17/16.
 */
public class InfiniteAbilityInventory extends AbilityInventory {
    private ArrayList<Ability> abilities;

    public InfiniteAbilityInventory(AbilityStorage owner) {
        super(owner);
        this.abilities = new ArrayList<>();
    }

    @Override
    public boolean addAbility(Ability ability) {
        if (!containsAbility(ability)) {
            abilities.add(ability);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAbilty(Ability ability) {
        if (containsAbility(ability)) {
            abilities.remove(ability);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAbility(Ability ability) {
        return abilities.contains(ability);
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Iterator<Ability> getIterator() {
        return abilities.iterator();
    }
}
