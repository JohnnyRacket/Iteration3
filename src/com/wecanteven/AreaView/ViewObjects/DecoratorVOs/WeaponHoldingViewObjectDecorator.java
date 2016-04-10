package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;


import com.wecanteven.AreaView.ViewObjects.FogOfWarViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.NullViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Observers.Directional;

import java.awt.*;


/**
 * Created by Alex on 4/4/2016.
 */
public class WeaponHoldingViewObjectDecorator extends DecoratorViewObject {
    private ViewObject weapon;
    public WeaponHoldingViewObjectDecorator(ViewObject child, Directional weaponSubject) {
        super(child);
        weapon = new NullViewObject(child.getPosition());
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        weapon.draw(g);
    }

    @Override
    public void addToFogOfWarViewObject(FogOfWarViewObject fogOfWarViewObject) {
        super.addToFogOfWarViewObject(fogOfWarViewObject);
        weapon.addToFogOfWarViewObject(fogOfWarViewObject);
    }
}
