package com.wecanteven.Models.Map.Terrain;

import com.wecanteven.Models.Entities.Entity;
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
    public Direction getDirection(){
        return direction;
    }
    public void interact(Entity entity){
        System.out.println("The entity is in the current");
        entity.move(direction);
    }
}
