package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.Takeable.TakeableItem;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class TakeableItemFactory {
    public TakeableItem vendTakeableItem(String name) {
        return new TakeableItem(name, 10);
    }
}
