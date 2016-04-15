package com.wecanteven.Models.BuffManager;

/**
 * Created by simonnea on 4/14/16.
 */
public class InterruptableBuff extends Buff {
    private boolean interrupted;

    public InterruptableBuff(int tickDuration, BuffApply apply, BuffUnapply unapply) {
        super(tickDuration, apply, unapply);
    }

    public void interrupt() {
        debuff();
        interrupted = true;
    }

    @Override
    public void alert() {
        if (!interrupted) {
            debuff();
        }
        owner.notifyInterruptedExpired(this);
    }

    @Override
    public void setOwner(BuffManager bm) {
        this.owner = bm;
        owner.addInterruptable(this);
    }
}
