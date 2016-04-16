package com.wecanteven.Models.BuffManager;

import com.wecanteven.Models.ModelTime.ModelTime;

/**
 * Created by simonnea on 4/14/16.
 */
public class InterruptableBuff extends Buff {
    private boolean interrupted;

    public InterruptableBuff(String name, int tickDuration, BuffApply apply, BuffUnapply unapply) {
        super(name, tickDuration, apply, unapply);
    }

    public void interrupt() {
        debuff();
        interrupted = true;
    }

    @Override
    public void alert() {
        if (active) {
            if (!interrupted) {
                debuff();
            }
        }
        owner.notifyInterruptedExpired(this);
    }

    @Override
    public void setOwner(BuffManager bm) {
        this.owner = bm;
        ModelTime.getInstance().registerAlertable(this, tickDuration);
        owner.addInterruptable(this);
    }
}
