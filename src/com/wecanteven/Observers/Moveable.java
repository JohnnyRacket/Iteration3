package com.wecanteven.Observers;

import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by simonnea on 3/31/16.
 */
public interface Moveable extends Positionable{
    int getMovingTicks();

    boolean move(Direction d);

    boolean move(Location l);

    boolean fall();

    void setDirection(Direction d);
}
