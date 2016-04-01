package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Map implements MapVisitable {

    private Column[][] columns;

    public Map(int rSize, int sSize){
        columns = new Column[rSize][sSize];
    }

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitMap(this);
    }

    public boolean add(Entity entity, Location loc){
        return columns[loc.getR()][loc.getS()].add(entity, loc.getZ());
    }
}
