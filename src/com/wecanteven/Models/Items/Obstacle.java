package com.wecanteven.Models.Items;


import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by simonnea on 3/31/16.
 */
public class Obstacle extends Item {

    public Obstacle(String name)
    {
        super(name);
    }

    public void accept(ItemVisitor visitor) {
        visitor.visit(this);
    }
}
