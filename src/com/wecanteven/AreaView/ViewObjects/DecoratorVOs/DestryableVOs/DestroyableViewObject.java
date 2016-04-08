package com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DestryableVOs;

import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecoratorViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;

/**
 * Created by Alex on 4/7/2016.
 */
public class DestroyableViewObject extends DecoratorViewObject {
    public DestroyableViewObject(ViewObject child) {
        super(child);
    }
}
