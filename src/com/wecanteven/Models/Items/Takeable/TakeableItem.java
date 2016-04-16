package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Observers.Destroyable;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.Visitors.ItemVisitor;

import java.util.ArrayList;

/**
 * Created by simonnea on 3/31/16.
 */
public class TakeableItem extends Item implements Destroyable, ViewObservable{
    private ArrayList<Observer> observers = new ArrayList<>();

    private int value;

    private boolean isDestroyed = false;
    public TakeableItem(String name, int value) {
        super(name);
        this.value = value;
    }
    public int getValue() {return value;}

    public void setIsDestoryed(boolean isDestoryed) {
        this.isDestroyed = isDestoryed;
    }

    public void interact(Character character) {
        isDestroyed = true;
        notifyObservers();
        character.pickup(this);
    }

    /**
     * Visitation Rights
     * */

    public void accept(ItemVisitor visitor) {
        visitor.visitTakeableItem(this);
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