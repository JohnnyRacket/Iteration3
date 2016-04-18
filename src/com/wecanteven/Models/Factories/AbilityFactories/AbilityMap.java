package com.wecanteven.Models.Factories.AbilityFactories;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.Character;

import java.util.HashMap;

/**
 * Created by simonnea on 4/18/16.
 */
public class AbilityMap {
    private  static AbilityMap map;
    private HashMap<String,IAbilityCreateCommand> commandMap;

    private AbilityMap() {
        commandMap = new HashMap<>();

        initialize();
    }

    public static AbilityMap getInstance() {
        if ( map == null) {
            map = new AbilityMap();
        }
        return map;
    }

    private void initialize() {
        AbilityFactory factory = new AbilityFactory();
        // ALL
        commandMap.put("BindWounds", (character -> factory.vendBindWounds(character)));

        // SUMMONER
        commandMap.put("Poison", (character) -> factory.vendPoisonAttack(character));
        commandMap.put("Slow", (character) -> factory.vendSlowAttack(character));
        commandMap.put("SpeedUp", (character) -> factory.vendSpeedUp(character));

        // SNEAK
        commandMap.put("PickPocket", (character) -> factory.vendPickPocket(character));
        commandMap.put("Creep", (character) -> factory.vendCreep(character));

        // SMASHER

    }

    public Ability getAbility(String name, Character caster) {
        if (commandMap.containsKey(name)) {
            return commandMap.get(name).create(caster);
        }

        throw new IllegalArgumentException("An ability with name: " + name + " does not exist.");
    }
}
