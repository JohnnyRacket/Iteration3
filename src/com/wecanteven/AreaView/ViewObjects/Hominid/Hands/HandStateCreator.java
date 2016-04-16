package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 4/15/2016.
 */
public interface HandStateCreator {
    HandState changeHandState(Direction d, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand);
}
