package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.JumpDetector;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecoratorViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.SimpleVOFactory;
import com.wecanteven.AreaView.ViewObjects.Hominid.Hands.HandsViewObject;
import com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies.MountMovement;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.Entities.Mount;
import com.wecanteven.Observers.Actionable;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Moveable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Config;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;

import java.awt.*;

/**
 * Created by adamfortier on 4/17/16.
 */
public class MountViewObject implements ViewObject, Observer {
    private MicroPositionableViewObject body;
    private SimpleVOFactory simpleVOFactory;
    private Mount subject;
    private Position position;
    private MountMovement mountMovement;
    private boolean isMounted = false;

    public MountViewObject(Position position, SimpleVOFactory simpleVOFactory, Mount mount) {
        this.simpleVOFactory = simpleVOFactory;
        this.position = position;
        this.subject = mount;
        this.mountMovement = new MountMovement(body, 1D);
        register();
        initBody();
    }

    private void register() {
        subject.attach(this);
    }

    private void initBody() {
        body = simpleVOFactory.createBubble(position, GameColor.GRAY);
    }

    public void createMount(GameColor color) {
        body = simpleVOFactory.createBubble(position, color);
    }

    public void move(long duration) {

       // mountMovement.execute(duration);
    }


    @Override
    public void update() {
        if(subjectMounted())
            isMounted = true;
        else if(subjectDismounted())
            isMounted = false;
        else
            move(subject.getMovingTicks()*Config.MODEL_TICK);
    }

    private boolean subjectDismounted() {
        return subject.getMounted() == false && isMounted == true;
    }

    private boolean subjectMounted() {
        return subject.getMounted() == true && isMounted == false;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        position = p;
        body.setPosition(p);
    }

    @Override
    public void draw(Graphics2D g) {
            body.draw(g);
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {

    }

}
