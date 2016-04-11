package com.wecanteven.AreaView.ViewObjects;


import com.wecanteven.AreaView.Position;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public interface ViewObject {
    Position getPosition();
    void setPosition(Position p);
    void draw(Graphics2D g);
    void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject);
}
