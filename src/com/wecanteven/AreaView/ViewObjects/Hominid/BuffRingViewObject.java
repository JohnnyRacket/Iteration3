package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.Factories.EquipableItemVOFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.SimpleVOFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.Models.BuffManager.BuffManager;
import com.wecanteven.Observers.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alex on 4/14/2016.
 */
public class BuffRingViewObject implements ViewObject, Observer{
    private ArrayList<Pair> buffs = new ArrayList<>();

    private EquipableItemVOFactory factory;

    private double r = 0.7;
    private double height = 4;
    private double tangent = 0;
    private final double TEMP_OFFSET = (new Random()).nextInt(20);
    long loopTime = 3000;

    private Position position;
    private BuffManager subject;

    public BuffRingViewObject(Position p, EquipableItemVOFactory factory, BuffManager subject) {
        this.factory = factory;
        this.position = p;

        this.subject = subject;
        subject.attach(this);
        update();
        adjustBuffs();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position p) {
        this.position = p;
        for (Pair pair: buffs) {
            pair.buff.setPosition(p);
        }
    }

    private void adjustBuffs() {
        double percentage = (double)(ViewTime.getInstance().getCurrentTime() % loopTime)/loopTime;
        for (Pair pair : buffs) {
            pair.buff.setOffsetAngle(pair.offset + percentage*Math.PI*2 + TEMP_OFFSET);
        }
        ViewTime.getInstance().register( this::adjustBuffs, 20);
    }

    @Override
    public void draw(Graphics2D g) {
        for (Pair pair: buffs) {
            pair.buff.draw(g);
        }
    }


    public void drawFront(Graphics2D g) {
        adjustBuffs();
        for (Pair pair: buffs) {
            if (pair.buff.getY() > 0)
            pair.buff.draw(g); {

            }
        }
    }

    private void setBuffs(ArrayList<String> buffNames) {
        buffs.clear();
        if (buffNames.size() == 0) return;
        double angleOffset = 2*Math.PI/buffNames.size();
        for (int i = 0; i < buffNames.size(); i++) {
            MicroPositionableViewObject buff = factory.createBuff(getPosition(), buffNames.get(i));
            initBuff(buff);
            buff.setOffsetAngle(angleOffset*i);
            buffs.add(new Pair(buff, angleOffset*i));
        }
    }

    public void initBuff(MicroPositionableViewObject buff) {
        buff.setHeight(height);
        buff.setRadius(r);
        buff.setTangent(tangent);
    }

    @Override
    public void update() {
        ArrayList<String> buffNames = new ArrayList<>();
        subject.getIterator().forEachRemaining( (buff) -> {
            buffNames.add(buff.getName());
        });
        setBuffs(buffNames);
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        //TODO: FoW for buffs?
    }

    private class Pair {
        public MicroPositionableViewObject buff;
        public double offset;

        public Pair(MicroPositionableViewObject buff, double offset) {
            this.buff = buff;
            this.offset = offset;
        }
    }
}
