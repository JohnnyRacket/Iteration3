package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.OneShot;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class OneShotItemFactory {
    // TODO flesh out factory
    public OneShot vendDefaultOneShot() {
        return new OneShot("Holla", (entity) -> entity.die());
    }
}
