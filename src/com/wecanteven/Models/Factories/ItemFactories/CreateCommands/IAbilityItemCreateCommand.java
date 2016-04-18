package com.wecanteven.Models.Factories.ItemFactories.CreateCommands;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.AbilityItem;

/**
 * Created by simonnea on 4/17/16.
 */
public interface IAbilityItemCreateCommand {
    AbilityItem create();
}
