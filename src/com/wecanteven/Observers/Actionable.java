package com.wecanteven.Observers;

import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 3/31/2016.
 */
public interface Actionable {
    int getWindUpTicks();
    int getCoolDownTicks();
    default int getAttackingTicks() {
        return getWindUpTicks() + getCoolDownTicks();
    }
}
