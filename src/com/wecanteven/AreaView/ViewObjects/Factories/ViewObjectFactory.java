package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.DirectionalViewObject;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 3/31/2016.
 */
public abstract class ViewObjectFactory {
    private HexDrawingStrategy hexDrawingStrategy = new HexDrawingStrategy();

    public HominidViewObject createSneak(Position p, Direction d) {
        DirectionalViewObject body = createBody(p, d, "Sneak");
        return new HominidViewObject(p, d, body);
    }

    private DirectionalViewObject createBody(Position p, Direction d, String entityName) {
        DynamicImage bodyNorth = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/north.xml");
        DynamicImage bodySouth = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/south.xml");
        DynamicImage bodyNorthEast = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/northeast.xml");
        DynamicImage bodyNorthWest = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/northwest.xml");
        DynamicImage bodySoutheast = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/southeast.xml");
        DynamicImage bodySouthWest = DynamicImageFactory.getInstance().loadDynamicImage("Entities/" + entityName + "/southwest.xml");
        return new DirectionalViewObject(p, d, hexDrawingStrategy, bodyNorth, bodySouth, bodyNorthEast, bodyNorthWest, bodySoutheast, bodySouthWest);
    }
}
