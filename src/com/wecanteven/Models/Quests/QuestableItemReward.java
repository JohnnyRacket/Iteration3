package com.wecanteven.Models.Quests;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.MenuView.ViewManager;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Items.Takeable.QuestedItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Storage.ItemStorage.Inventory;
import com.wecanteven.ViewEngine;

/**
 * Created by Joshua Kegley on 4/16/2016.
 */
public class QuestableItemReward implements Questable {

    private QuestedItem questedItem;
    private TakeableItem rewardItem;

    public QuestableItemReward(QuestedItem questItem, TakeableItem rewardItem) {
        this.questedItem = questItem;
        this.rewardItem = rewardItem;
    }

    @Override
    public boolean completeQuest(Character quester) {
        Inventory questerInventory = quester.getItemStorage().getInventory();
        if (questerInventory.contains(questedItem)) {
            if(!questerInventory.isFull()){
                quester.pickup(rewardItem);
                return true;
            }else{
                UIViewFactory.getInstance().createToast(4, "Your Inventory is full!");
            }
        }
        return false;
    }

    public QuestedItem getQuestedItem() {
        return questedItem;
    }
}
