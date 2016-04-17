package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.AIController;
import com.wecanteven.Controllers.AIControllers.Targets.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.UtilityClasses.*;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;
import com.wecanteven.Visitors.TargetVisitor;

import java.util.*;

/**
 * Created by John on 4/5/2016.
 */
public abstract class AbstractActionController implements TargetVisitor{

    private Character character;
    private com.wecanteven.Models.Map.Map map;
    private AIController controller;

    public AbstractActionController(Character character, com.wecanteven.Models.Map.Map map){
        this.character = character;
        this.map = map;
    }
    public void act(Target target){
        target.accept(this);
    }

    public Direction getPathToTarget(Target target){
        Queue<Location> queue = new LinkedList<>();
        List<Location> visited = new LinkedList<>();
        java.util.Map<Location, Location> parentMap = new HashMap<>();
        Location targetLocation = target.getLocation();
        Iterator<Location> iter;
        CanMoveVisitor canMoveVisitor = character.getCanMoveVisitor();
        //Map map = Map.getInstance();
        ArrayList<Location> searchableArea = controller.getSearchArea();

        //System.out.println("done building searchable area");

        queue.add(character.getLocation());
        parentMap.put(character.getLocation(), null);

        while(!queue.isEmpty()){

            Location currLocation = queue.remove();
            if(!visited.contains(currLocation)) {
                visited.add(currLocation);
               //System.out.println("building path");
                //System.out.println("Comparing R's: " + currLocation.getR() + "=" + targetLocation.getR() + " and S's: " + currLocation.getS() + "=" + targetLocation.getS());
                if (currLocation.equals(targetLocation)) {
                    //we found the thing
                    Location prev = null;
                    Location curr = currLocation;

                    while (!curr.equals(character.getLocation())) {
                        prev = curr;
                        curr = parentMap.get(curr);
                    }
                    //System.out.println("Direction returned is: " + DirectionFinder.getDirection(character.getLocation(), prev));
                    return DirectionFinder.getDirection(character.getLocation(), prev);
                }
                for(int i = 0; i < character.getJumpHeight(); ++i) {
                    Location loc = new Location(currLocation.getR(),currLocation.getS(),i);
                    iter = new HexRing(1, loc).iterator();//HexMath.sortedRing(currLocation, 1);

                    while (iter.hasNext()) {
                        Location tmpLocation = iter.next();
                        if(searchableArea.contains(tmpLocation)) {
                            try {
                                map.getTile(tmpLocation).accept(canMoveVisitor);
                                if ((canMoveVisitor.canMove() && !visited.contains(tmpLocation)) || tmpLocation.equals(targetLocation)) {
                                    if (!parentMap.containsKey(tmpLocation)) {
                                        queue.add(tmpLocation);
                                    }
                                    if (!parentMap.containsKey(tmpLocation)) {
                                        parentMap.put(tmpLocation, currLocation);
                                    }
                                }
                            } catch (Exception e) {
                                //System.out.println("out of map bounds");
                            }
                        }//end if of searchable area contains
                    }
                }
            }else{
                //System.out.println("cockblocked");
            }
        }
        System.out.println("this should not happen");
        return null;
    }

    protected boolean checkLocation(Target target, int distance){
        if(target != null) {
            Iterator<Location> iter = new Filled3DHex(this.getCharacter().getLocation(), distance).iterator();
            while(iter.hasNext()){
                if(iter.next().equals(target.getLocation())){
                    return true;
                }
            }
            //will only get here to return true if the target is in desired location
            return false;
        }else{return false;}
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public com.wecanteven.Models.Map.Map getMap() {
        return map;
    }

    public void setMap(com.wecanteven.Models.Map.Map map) {
        this.map = map;
    }

    public AIController getController() {
        return controller;
    }

    public void setController(AIController controller) {
        this.controller = controller;
    }
}
