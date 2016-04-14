package com.wecanteven.Controllers.AIControllers.SearchingControllers;

import com.wecanteven.Controllers.AIControllers.ActionControllers.AbstractActionController;
import com.wecanteven.Controllers.AIControllers.Targets.NullTarget;
import com.wecanteven.Controllers.AIControllers.Targets.Target;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.UtilityClasses.HexRing;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.MapVisitor;
import com.wecanteven.Visitors.OccupationVisitor;

import java.util.Iterator;

/**
 * Created by John on 4/5/2016.
 */
public abstract class AbstractSearchingController implements MapVisitor, EntityVisitor, OccupationVisitor{

    private Target highPriorityTarget = new NullTarget();
    private Character character;
    private int searchRadius;
    private Map map;

    public AbstractSearchingController(Character character, Map map, int searchRadius){
        this.character = character;
        this.map = map;
        this.searchRadius = searchRadius;
    }


    public Target search(){
        highPriorityTarget = new NullTarget();
        Location currentLocation = character.getLocation();

        Iterator<Location> searchArea = new HexRing(searchRadius, currentLocation).iterator();

        Iterator<Location> iter;
        while (searchArea.hasNext()) {
            Location current = searchArea.next();
            Tile currentTile = map.getTile(current);
            currentTile.accept(this);
        }
        return highPriorityTarget;
    }
}
