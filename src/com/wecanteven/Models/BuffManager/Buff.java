package com.wecanteven.Models.BuffManager;

import com.wecanteven.Models.ModelTime.Alertable;

/**
 * Created by simonnea on 4/14/16.
 */
public class Buff implements Alertable {
    private int tickDuration;
    protected BuffManager owner;

    private BuffApply apply;
    private BuffUnapply unapply;

    public Buff(int tickDuration, BuffApply apply, BuffUnapply unapply) {
        this.tickDuration = tickDuration;
        this.apply = apply;
        this.unapply = unapply;
    }

    public void buff() {
        owner.apply(apply);
    }

    public void debuff() {
        owner.unapply(unapply);
    }

    @Override
    public void alert() {
        debuff();

        owner.notifyExpired(this);
    }

    public void setOwner(BuffManager bm) {
        this.owner = bm;
        owner.addUninterruptable(this);
    }
}
