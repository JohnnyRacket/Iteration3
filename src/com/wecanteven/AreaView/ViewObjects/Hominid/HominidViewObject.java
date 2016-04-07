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
    //private FeetViewObject feet;

    public HominidViewObject(Position position, Direction direction, Directional directionSubject, Moveable movingSubject, DirectionalViewObject body, HandsViewObject hands) {
        this.position = position;
        this.direction = direction;
        this.directionSubject = directionSubject;
        this.movingSubject = movingSubject;
        this.body = body;
        this.hands = hands;
        //this.feet = feet;

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
    }

    @Override
    public void draw(Graphics2D g) {
        hands.drawBackground(g);
        body.draw(g);
        hands.drawForeground(g);
        //feet.draw(g);
    }

    @Override
    public void update() {
        this.direction = directionSubject.getDirection();
        updateHandsDirection();
        if (subjectHasMoved()) {
            hands.move(movingSubject.getMovingTicks()*Config.MODEL_TICK);
        }
    }



    private void updateHandsDirection() {
        //System.out.println("changing hand direction");
        hands.changeDirection(direction);
    }

    private void updateHandsPosition() {
        hands.setPosition(position);
    }

    private void updateBodyPosition() {
        this.body.setPosition(position);
    }


    private long endMoveTime = 0;
    public boolean subjectHasMoved() {
        long currentTime = ViewTime.getInstance().getCurrentTime();
        System.out.println("Moving Ticks: " + movingSubject.getMovingTicks());
        if (currentTime >= endMoveTime && movingSubject.getMovingTicks() > 0) {
            endMoveTime = currentTime + movingSubject.getMovingTicks()* Config.MODEL_TICK;
            return true;
        }
        return false;
    }
}



/*
    On direction change.. send to hands which needs to get angle and add pi/2 for one hand and -pi/2 for the other (do this in the hand state)
    init the hands in the state instead of the factory as well.


 */
