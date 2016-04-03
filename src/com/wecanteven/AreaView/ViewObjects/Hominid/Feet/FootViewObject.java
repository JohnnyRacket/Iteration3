package com.wecanteven.AreaView.ViewObjects.Hominid.Feet;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.LeafViewObject;
import com.wecanteven.UtilityClasses.Direction;

import java.awt.*;

/**
 * Created by Alex on 4/2/2016.
 */
public class FootViewObject extends DirectionalViewObject {
    public FootViewObject(Position position, Direction direction, DynamicImageDrawingStrategy drawingStrategy, DynamicImage north, DynamicImage south, DynamicImage northeast, DynamicImage northwest, DynamicImage southeast, DynamicImage southwest) {
        super(position, direction, drawingStrategy, north, south, northeast, northwest, southeast, southwest);
    }


}
