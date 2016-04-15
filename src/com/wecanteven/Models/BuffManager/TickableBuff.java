package com.wecanteven.Models.BuffManager;

import com.wecanteven.Models.ModelTime.ModelTime;

/**
 * Created by simonnea on 4/14/16.
 */
public class TickableBuff extends Buff {
    private int tickDuration;

    public TickableBuff(String name, int tickDuration, BuffApply apply, BuffUnapply unapply) {
        super(name, tickDuration, apply, unapply);

        this.tickDuration = tickDuration;
    }

    @Override
    public void setOwner(BuffManager bm) {
        this.owner = bm;
        ModelTime.getInstance().registerAlertable(this, 30);
        tickDuration -= 30;
        owner.addTickable(this);
    }

    @Override
    public void alert() {
        if (active) {
            if (tickDuration <= 0) {
                super.alert();
            } else {
                buff();
                ModelTime.getInstance().registerAlertable(this, 30);
                tickDuration -= 30;
            }
        }
    }
}
