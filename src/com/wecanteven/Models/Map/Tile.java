package com.wecanteven.Models.Map;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.TakeableItem;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.MapVisitor;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class Tile implements MapVisitable {

    private Obstacle obstacle;
    private InteractiveItem item;
    private ArrayList<TakeableItem> takeableItems;
    private Entity entity;


    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitTile(this);
    }

    public boolean add(Entity entity){
        if(this.entity == null){
            this.entity = entity;
            return true;
        }else{
            return false;
        }
    }
}
