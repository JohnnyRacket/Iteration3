package com.wecanteven.AreaView.ViewObjects.DrawingStategies;


import com.wecanteven.AreaView.Position;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public interface StringDrawingStrategy {
    void draw(Graphics g, String text, Position position);
}
