package com.wecanteven.Models.Factories.AbilityFactories;

import java.util.HashMap;

/**
 * Created by simonnea on 4/17/16.
 */
public class AbilityItemMap {
    private static AbilityItemMap map;

    private HashMap<String, IAbilityItemCreateCommand> abilityMap;

    private AbilityItemMap() {
        abilityMap = new HashMap<>();

        initialize();
    }

    private void initialize() {
        AbilityItemFactory factory = new AbilityItemFactory();

        abilityMap.put("Bind Wounds Ability", () -> factory.vendBindWounds("Bind Wounds Ability", 100) );
        abilityMap.put("Observation", () -> factory.vendObservation("Observation", 100));
    }
}
