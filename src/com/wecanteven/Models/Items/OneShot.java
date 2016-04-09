package com.wecanteven.Models.Items;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Visitors.ItemVisitor;

import java.util.ArrayList;

/**
 * Created by simonnea on 3/31/16.
 */
public class OneShot extends Item implements Destroyable, Observable{
    private ArrayList<Observer> observers = new ArrayList<>();

    private boolean isDestroyed = false;
    private ItemAction itemAction;

    public OneShot(String name, ItemAction itemAction) {
        super(name);
        this.itemAction = itemAction;
    }

    public void interact(Entity entity) {
        isDestroyed = true;
        itemAction.execute(entity);
        notifyObservers();
        System.out.println("DESTROYED A BOX");
    }

    /**
     * Visitation Rights
     * */

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visitOneShotItem(this);
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
