package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Factories.AbilityFactories.AbilityFactory;
import com.wecanteven.Models.Items.Takeable.AbilityItem;

/**
 * Created by simonnea on 4/17/16.
 */
public class AbilityItemFactory {
    private static AbilityFactory factory = new AbilityFactory();

    public AbilityItem vendBindWounds(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendBindWounds(character));
            }
        };
    }

    public AbilityItem vendObservation(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
            }
        };
    }

    public AbilityItem vendOneHandedWeapon(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendOneHandedWeapon(character));
            }
        };
    }

    public AbilityItem vendTwoHandedWeapon(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendTwoHandedWeapon(character));
            }
        };
    }

    public AbilityItem vendBrawling(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendBrawling(character));
            }
        };
    }



}
