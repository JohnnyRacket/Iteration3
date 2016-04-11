package com.wecanteven.AreaView.ViewObjects.DrawingStategies;

import com.wecanteven.AreaView.Position;
import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by Alex on 4/11/2016.
 */
public interface MenuComponentDrawingStrategy {
    void draw(Graphics2D g, Drawable drawable, Position p);
}
