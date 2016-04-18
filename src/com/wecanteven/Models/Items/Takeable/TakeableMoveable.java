package com.wecanteven.Models.Items.Takeable;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.ModelTime.ModelTime;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanFallVisitors.CanFallVisitor;
import com.wecanteven.Visitors.CanFallVisitors.TerranianCanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.CanMoveVisitor;
import com.wecanteven.Visitors.CanMoveVisitors.ItemCanMoveVisitor;
import com.wecanteven.Visitors.ItemVisitor;

/**
 * Created by Cachorrita on 4/16/2016.
 */
public class TakeableMoveable extends TakeableItem implements Moveable {
    private ActionHandler actionHandler;
    private TakeableItem item;
    private int movingTicks;
    private boolean isActive;
    private CanMoveVisitor canMoveVisitor;
    private CanFallVisitor canFallVisitor;

    public TakeableMoveable(String name, int value, TakeableItem item, ActionHandler handler, Location location) {
        super(name, value);
        this.item = item;
        this.actionHandler = handler;
        setLocation(location);
        setMovingTicks(0);
        setCanMoveVisitor(new ItemCanMoveVisitor());
        setCanFallVisitor(new TerranianCanFallVisitor());

    }

    @Override
    public int getMovingTicks() {
        return movingTicks;
    }

    @Override
    public boolean move(Direction d) {
        if (actionHandler != null) {
            Location destination = getLocation().add(d.getCoords);

            return actionHandler.move(this, destination, getMovingTicks());
        }
        return false;
    }


    @Override
    public boolean move(Location l) {
        if (actionHandler != null) {
            return actionHandler.move(this, l, getMovingTicks());
        }
        return false;
    }

    @Override
    public boolean fall() {
        if (actionHandler != null) {
            Location tileBelow = getLocation().subtract(new Location(0, 0, 1));
            return actionHandler.fall(this, tileBelow);
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



    public boolean isActive(){
        return isActive;
    }

    public void updateMovingTicks(int ticks) {
        setMovingTicks(ticks);
        calculateActiveStatus();
        tickTicks();
        notifyObservers();
    }

    public void setMovingTicks(int movingTicks){
        this.movingTicks = movingTicks;
    }

    protected void tickTicks(){
        if(isActive()){
            ModelTime.getInstance().registerAlertable(() -> {
                deIncrementMovingTick();
                calculateActiveStatus();
                tickTicks();
            }, 1);
        }
    }

    protected void deIncrementMovingTick(){
        if(getMovingTicks()>0)
            movingTicks--;
    }

    public CanMoveVisitor getCanMoveVisitor() {
        return canMoveVisitor;
    }

    public void setCanMoveVisitor(CanMoveVisitor canMoveVisitor) {
        this.canMoveVisitor = canMoveVisitor;
    }
    public void setCanFallVisitor(CanFallVisitor canFallVisitor) {
        this.canFallVisitor = canFallVisitor;
    }

    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public void setActionHandler(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public boolean calculateActiveStatus(){
        if(getMovingTicks() <= 0 && getMovingTicks() <= 0){
            setIsActive(false);
            return false;
        }
        else{
            setIsActive(true);
            return true;
        }
    }

    public void setIsActive(boolean isActive){
            this.isActive = isActive;
    }
}
