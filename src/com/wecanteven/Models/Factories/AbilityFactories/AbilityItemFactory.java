package com.wecanteven.Models.Factories.AbilityFactories;

import com.wecanteven.Models.Entities.Character;
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


}
