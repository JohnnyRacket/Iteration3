package com.wecanteven.AreaView.ViewObjects;


import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.AreaView.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/29/2016.
 */
public class TileViewObject implements ViewObject {
    private Location location;
    private ArrayList<ViewObject> children = new ArrayList<>();

    public TileViewObject(Location location) {
        this.location = location;
    }

    public void add(ViewObject vo) {
        this.children.add(vo);
    }

    private int loomingDeathCounter = 0;

    public void remove(ViewObject vo) {
        if (!this.children.remove(vo)) {
            System.out.println("LOOMING DEATH IS APPROACHING THE VIEW! Danger level: " + loomingDeathCounter++);
            //ViewTime.getInstance().register(() -> this.remove(vo), 0);
            System.out.println("BAD SHIT HAPPENED IN: TileViewObject.remove(ViewObject)");
        } else {
            if (loomingDeathCounter > 0) {
                System.out.print("WOOT WOOT!!!!!! Looming death averted");
                loomingDeathCounter = 0;
            }
        }
    }

    @Override
    public Position getPosition() {
        return location.toPosition();
    }

    @Override
    public void setPosition(Position p) {
        this.location = p.getLocation();
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void draw(Graphics2D g) {
        for (ViewObject vo: children) {
            vo.draw(g);
        }
    }
}
