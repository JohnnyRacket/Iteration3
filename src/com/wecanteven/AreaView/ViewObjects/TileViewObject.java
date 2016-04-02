package com.wecanteven.AreaView.ViewObjects;


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

    public void remove(ViewObject vo) {
        if (!this.children.remove(vo)) {
            System.out.println("BAD SHIT HAPPENED IN: TileViewObject.remove(ViewObject)");
        };
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
