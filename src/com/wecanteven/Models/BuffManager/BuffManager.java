package com.wecanteven.Models.BuffManager;

import com.wecanteven.Models.Entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simonnea on 4/14/16.
 */
public class BuffManager {
    private Entity owner;

    private List<Buff> buffList = new ArrayList<>();
    private List<InterruptableBuff> interruptables = new ArrayList<>();

    public BuffManager(Entity owner) {
        this.owner = owner;
    }

    public void addBuff(Buff buff) {
        buff.setOwner(this);
    }

    public void addUninterruptable(Buff buff) {
        buffList.add(buff);
    }

    public void addInterruptable(InterruptableBuff buff) {
        interruptables.add(buff);
    }

    public void apply(BuffApply buff) {
        buff.apply(owner);
    }

    public void unapply(BuffUnapply debuff) {
        debuff.apply(owner);
    }

    public void notifyExpired(Buff buff) {
        buffList.remove(buff);
    }

    public void notifyInterruptedExpired(InterruptableBuff buff) {
        interruptables.remove(buff);
    }

    public void interrupt() {
        for (InterruptableBuff ib: interruptables) {
            ib.interrupt();
        }
    }
}
