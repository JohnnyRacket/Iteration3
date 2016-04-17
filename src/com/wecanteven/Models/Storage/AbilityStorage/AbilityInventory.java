package com.wecanteven.Models.Storage.AbilityStorage;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Storage.StorageComponent;

import java.util.Iterator;

/**
 * Created by simonnea on 4/17/16.
 */
public abstract class AbilityInventory extends StorageComponent<AbilityStorage> {

    public AbilityInventory(AbilityStorage owner) {
        super(owner);
    }

    public abstract boolean addAbility(Ability ability);
    public abstract boolean removeAbilty(Ability ability);
    public abstract boolean containsAbility(Ability ability);
    public abstract boolean isFull();
    public abstract Iterator<Ability> getIterator();
}
