package com.wecanteven.Models.Factories.AbilityFactories;

import com.wecanteven.Models.Abilities.Ability;
import com.wecanteven.Models.Entities.Character;

/**
 * Created by Brandon on 4/18/2016.
 */
public interface IAbilityCreateCommand {
    Ability create(Character caster);
}
