package com.wecanteven.Models.BuffManager;

import com.wecanteven.Models.ModelTime.Alertable;
import com.wecanteven.Models.ModelTime.ModelTime;

/**
 * Created by simonnea on 4/14/16.
 */
public class Buff implements Alertable {
    private String name;
    protected int tickDuration;
    protected BuffManager owner;
    protected boolean active;

    private BuffApply apply;
    private BuffUnapply unapply;

    public Buff(String name, int tickDuration, BuffApply apply, BuffUnapply unapply) {
        this.name = name;
        this.tickDuration = tickDuration;
        this.apply = apply;
        this.unapply = unapply;
        this.active = true;
    }

    public void buff() {
        owner.apply(apply);
    }

    public void debuff() {
        owner.unapply(unapply);
    }

    public void disable() {
        active = false;
    }

    public void enable() {
        active = true;
    }

    @Override
    public void alert() {
        if (active) {
            debuff();
        }
        owner.notifyExpired(this);
    }

    public void setOwner(BuffManager bm) {
        this.owner = bm;
        ModelTime.getInstance().registerAlertable(this, tickDuration);
        owner.addUninterruptable(this);
        owner.apply(apply);
    }

    public String getName() {
        return name;
    }
}
