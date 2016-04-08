package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.InteractiveItem;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class InteractiveItemFactory {
    public InteractiveItem vendDefaultInteractiveItem(String name) {
        return new InteractiveItem(name);
    }
}
