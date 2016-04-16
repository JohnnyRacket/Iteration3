package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.Targets.*;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.TargetVisitor;

import java.util.*;

/**
 * Created by John on 4/5/2016.
 */
public abstract class AbstractActionController implements TargetVisitor{
    public void act(Target target){
        target.accept(this);
    }

    public Direction getPathToTarget(Target target){
//        Queue<Location> queue = new LinkedList<>();
//        List<Location> visited = new LinkedList<>();
//        java.util.Map<Location, Location> parentMap = new HashMap<>();
//        Location targetLocation = target.getLocation();
//        Iterator<Location> iter;
//        CanMoveVisitor canMoveVisitor = new NonTeleMoveVisitor();
//        Map map = Map.getInstance();
//
//        queue.add(entity.getLocation());
//        parentMap.put(entity.getLocation(), null);
//
//        while(!queue.isEmpty()){
//
//            Location currLocation = queue.remove();
//            if(!visited.contains(currLocation)) {
//                visited.add(currLocation);
//                //
//                if (currLocation.equals(targetLocation)) {
//                    //we found the thing
//                    Location prev = null;
//                    Location curr = currLocation;
//
//                    while (!curr.equals(entity.getLocation())) {
//                        prev = curr;
//                        curr = parentMap.get(curr);
//                    }
//                    return HexMath.getLocationDirection(entity.getLocation(), prev);
//                }
//                iter = HexMath.sortedRing(currLocation, 1);
//                while (iter.hasNext()) {
//                    Location tmpLocation = iter.next();
//                    try {
//                        map.getTile(tmpLocation).accept(canMoveVisitor);
//                        if ((canMoveVisitor.canMove() && !visited.contains(tmpLocation)) || tmpLocation.equals(targetLocation)) {
//                            if (!parentMap.containsKey(tmpLocation)) {
//                                queue.add(tmpLocation);
//                            }
//                            if (!parentMap.containsKey(tmpLocation)) {
//                                parentMap.put(tmpLocation, currLocation);
//                            }
//                        }
//                    } catch (Exception e) {
//                        //
//                    }
//                }
//            }
//        }

        return null;
    }
}
