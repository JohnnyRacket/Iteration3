package com.wecanteven.Models.Items;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class InteractiveItem extends Item {

    public InteractiveItem(String name) {
        super(name);
    }

    /**
     * Visitation rights
     * */

    public void accept(ItemVisitor visitor) {
        visitor.visitInteractiveItem(this);
    }

    /**
     * Class methods
     * */

    public void trigger() {
        /*?????????*/
    }
}
