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

    public AbilityItem vendSpeedUp(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendSpeedUp(character));
            }
        };
    }

    public AbilityItem vendPickPocket(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendPickPocket(character));
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

    public AbilityItem vendPoisonItem(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendPoisonAttack(character));
            }
        };
    }

    public AbilityItem vendSlowItem(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendSlowAttack(character));
            }
        };
    }

    public AbilityItem vendCreep(String name, int value) {
        return new AbilityItem(name, value) {
            @Override
            public void use(Character character) {
                character.addAbility(factory.vendCreep(character));
            }
        };
    }
}
