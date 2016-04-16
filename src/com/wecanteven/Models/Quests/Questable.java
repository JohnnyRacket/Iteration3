package com.wecanteven.Models.Quests;

import com.wecanteven.Models.Entities.Character;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public interface Questable {
    public boolean completeQuest(Character quester);
}
