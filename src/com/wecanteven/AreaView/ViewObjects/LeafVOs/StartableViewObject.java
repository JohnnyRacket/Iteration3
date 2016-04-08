package com.wecanteven.AreaView.ViewObjects.LeafVOs;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;

import java.awt.*;

/**
 * Created by Alex on 4/7/2016.
 */
public class StartableViewObject extends LeafViewObject {
    private DynamicImageDrawingStrategy drawingStrategy;

    public StartableViewObject(Position position) {
        super(position);
    }

    public void start() {

    }

    @Override
    public void draw(Graphics2D g) {

    }
}
