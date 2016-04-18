package com.wecanteven.Models.Items.Takeable.Equipable.Weapons;

import com.wecanteven.Models.Factories.AbilityFactories.AbilityFactory;
import com.wecanteven.Models.Factories.AbilityFactories.IAbilityCreateCommand;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Models.Storage.ItemStorage.Equipment;
import com.wecanteven.Visitors.WeaponsVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public abstract class WeaponEquipableItem extends EquipableItem {
    private IAbilityCreateCommand createAbility;
    private AbilityFactory factory;
    public WeaponEquipableItem(String name, int value, StatsAddable stats) {
        super(name, value, stats);
        factory = new AbilityFactory();
        setCreateAbility((caster)->{
            return getFactory().vendBrawling(caster);
        });
    }

    @Override
    public final void equip(Equipment equipment) {
        equipment.equipWeapon(this);
    }

    @Override
    public final void unequip(Equipment equipment) {
        equipment.unequipWeapon(this);
    }

    public abstract void accept(WeaponsVisitor weaponsVisitor);

    public void setCreateAbility(IAbilityCreateCommand createAbility){
        this.createAbility = createAbility;
    }

    public IAbilityCreateCommand getAbility(){
        return createAbility;
    }
    public AbilityFactory getFactory(){
        return factory;
    }
}
