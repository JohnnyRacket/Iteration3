package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

/**
 * Created by Alex on 3/31/2016.
 */
public class HominidViewObject implements ViewObject, Observer{
    private Position position;
    private Direction direction;
    private DirectionalViewObject body;

    private Directional directionSubject;
    private Moveable movingSubject;

    private HandsViewObject hands;
    private FeetViewObject feet;
    //private FeetViewObject feet;

    private Location lastLocation;

    public HominidViewObject(Position position, Direction direction, Directional directionSubject, Moveable movingSubject, DirectionalViewObject body, HandsViewObject hands, FeetViewObject feet) {
        this.position = position;
        this.direction = direction;
        this.directionSubject = directionSubject;
        this.movingSubject = movingSubject;
        this.body = body;
        this.hands = hands;
        this.feet = feet;
        this.lastLocation = movingSubject.getLocation();
        direction.setDirectionOf(body);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        this.position = p;
        updateComponentsPosition();
    }

    private void updateComponentsPosition() {
        updateBodyPosition();
        updateHandsPosition();
        updateFeetPosition();
    }

    private void updateFeetPosition() {
        feet.setPosition(position);
    }

    @Override
    public void draw(Graphics2D g) {
        hands.drawBackground(g);
        feet.draw(g);
        body.draw(g);
        hands.drawForeground(g);

    }

    @Override
    public void update() {
        if (subjectChangedDirection()) {
            changeDirection();
        }
        if (subjectHasMoved()) {
            if (isFalling()) {
                fall();
            } else if (isJumping()) {
                jump();
            } else if(isFalling()) {
                fall();
            } else {
                move();
            }
            lastLocation = movingSubject.getLocation();
        }
    }


    private boolean subjectHasMoved() {
        long currentTime = ViewTime.getInstance().getCurrentTime();
        System.out.println("Moving Ticks: " + movingSubject.getMovingTicks());
        if (currentTime >= endMoveTime && movingSubject.getMovingTicks() > 0) {
            endMoveTime = currentTime + movingSubject.getMovingTicks()* Config.MODEL_TICK;
            return true;
        }
        return false;
    }

    private boolean subjectChangedDirection() {
        return this.direction != directionSubject.getDirection();
    }

    private void move() {
        hands.move(movingSubject.getMovingTicks()*Config.MODEL_TICK);
        feet.move(movingSubject.getMovingTicks()*Config.MODEL_TICK);
    }
    private void jump() {
        hands.jump(movingSubject.getMovingTicks()*Config.MODEL_TICK);
        feet.jump(movingSubject.getMovingTicks()*Config.MODEL_TICK);

    }
    private void fall() {
        hands.fall(movingSubject.getMovingTicks()*Config.MODEL_TICK);
        feet.fall(movingSubject.getMovingTicks()*Config.MODEL_TICK);
    }

    private void changeDirection() {
        updateMyDirection();
        updateHandsDirection();
        updateFeetDirection();
    }

    private void updateFeetDirection() {
        feet.changeDirection(direction);
    }


    private void updateMyDirection() {
        this.direction = directionSubject.getDirection();
    }

    private void updateHandsDirection() {
        //System.out.println("changing hand direction");
        hands.changeDirection(directionSubject.getDirection());
    }

    private void updateHandsPosition() {
        hands.setPosition(position);
    }

    private void updateBodyPosition() {
        this.body.setPosition(position);
    }


    private boolean isFalling() {
        return movingSubject.getLocation().getZ() < lastLocation.getZ();
    }
    private boolean isJumping() {
        return movingSubject.getLocation().getZ() > lastLocation.getZ();
    }

    private long endMoveTime = 0;

}



/*
    On direction change.. send to hands which needs to get angle and add pi/2 for one hand and -pi/2 for the other (do this in the hand state)
    init the hands in the state instead of the factory as well.


 */
