package com.wecanteven.AreaView.ViewObjects.Hominid.Hands;

import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 4/17/2016.
 */
public class AnimatedHandState extends BrawlingState{
    private StartableViewObject leftWeapon;
    private StartableViewObject rightWeapon;

    private StartableViewObject curretnWeapon;

    public AnimatedHandState(Direction direction, MicroPositionableViewObject leftHand, MicroPositionableViewObject rightHand, StartableViewObject leftWeapon, StartableViewObject rightWeapon) {
        super(direction, leftHand, rightHand);
        this.leftWeapon = leftWeapon;
        this.rightWeapon = rightWeapon;
        this.curretnWeapon = this.rightWeapon;
    }

    @Override
    public void attack(long windUp, long coolDown) {
        super.attack(windUp, coolDown);

        curretnWeapon.start(windUp);
        ViewTime.getInstance().register( () -> {
            leftWeapon.reset();
            rightWeapon.reset();
        }, windUp + coolDown/2);

        if (curretnWeapon == leftWeapon) curretnWeapon = rightWeapon;
        else curretnWeapon = leftWeapon;


    }
}
