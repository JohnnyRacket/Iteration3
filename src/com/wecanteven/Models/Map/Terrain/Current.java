package com.wecanteven.Models.Map.Terrain;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class Current extends Terrain {
    private Direction direction;
    public Current(Direction direction){
        this.direction = direction;
    }

    @Override
    public void accept(TerrainVisitor visitor) {
        visitor.visitCurrent(this);
    }

    public String getTerrain() {
        return "Current";
    }

    @Override
    public void interact(Moveable moveable) {
        System.out.println("Moveable entered Current");
        //TODO need to update entity direction
        moveable.setDirection(direction);
        moveable.move(direction);
    }

    public Direction getDirection(){
        return direction;
    }
}
