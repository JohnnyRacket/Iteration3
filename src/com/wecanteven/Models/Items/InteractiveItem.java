package com.wecanteven.Models.Items;
import com.wecanteven.Observers.Activatable;
import com.wecanteven.Observers.Observable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Visitors.ItemVisitor;

import java.util.ArrayList;


/**
 * Created by simonnea on 3/31/16.
 */
public class InteractiveItem extends Item implements Activatable, Observable {
    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    private boolean isPressed = false;

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
        isPressed = true;
        notifyObservers();
    }
    public void release() {
        isPressed = false;
        notifyObservers();
    }

    @Override
    public boolean isActive() {
        return isPressed;
    }
}
