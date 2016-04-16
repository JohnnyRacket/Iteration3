package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by Cachorrita on 4/16/2016.
 */
public class TakeableMoveable extends TakeableItem implements Moveable {
    private ActionHandler handler;
    private TakeableItem item;

    public TakeableMoveable(String name, int value, TakeableItem item, ActionHandler handler, Location location) {
        super(name, value);
        this.item = item;
        this.handler = handler;
        setLocation(location);
    }

    @Override
    public int getMovingTicks() {
        return 30;
    }

    @Override
    public boolean move(Direction d) {
        if (handler != null) {
            Location destination = getLocation().add(d.getCoords);

            return handler.move(this, destination, getMovingTicks());
        }
        return false;
    }

    @Override
    public boolean move(Location l) {
        if (handler != null) {
            return handler.move(this, l, getMovingTicks());
        }
        return false;
    }

    @Override
    public boolean fall() {
        if (handler != null) {
            Location tileBelow = getLocation().subtract(new Location(0, 0, 1));
            return handler.fall(this, tileBelow);
        }
        return false;
    }

    @Override
    public void setDirection(Direction d) {

    }

    public TakeableItem extractItem() {
        return item;
    }

    @Override
    public void accept(ItemVisitor visitor) {
        visitor.visitTakeableItem(item);
    }
}
