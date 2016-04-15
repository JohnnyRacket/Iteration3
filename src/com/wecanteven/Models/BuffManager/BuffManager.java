package com.wecanteven.Models.BuffManager;

import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.ModelTime.ModelTime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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

    void addUninterruptable(Buff buff) {
        buffList.add(buff);
    }

    void addInterruptable(InterruptableBuff buff) {
        interruptables.add(buff);
    }

    void addTickable(TickableBuff buff) {
        buffList.add(buff);
    }

    void apply(BuffApply buff) {
        buff.apply(owner);
    }

    void unapply(BuffUnapply debuff) {
        debuff.apply(owner);
    }

    void notifyExpired(Buff buff) {
        buffList.remove(buff);
    }

    void notifyInterruptedExpired(InterruptableBuff buff) {
        interruptables.remove(buff);
    }

    public void interrupt() {
        for (InterruptableBuff ib: interruptables) {
            ib.interrupt();
        }
    }

    public void clearAll() {
        Iterator<Buff> iter = getIterator();

        while (iter.hasNext()) {
            Buff buff = iter.next();
            buff.debuff();
            buff.disable();
        }
    }

    public Iterator<Buff> getIterator() {
        List<Buff> allBuffs = new ArrayList<>();

        allBuffs.addAll(buffList);
        allBuffs.addAll(interruptables);

        allBuffs.sort((Buff o1, Buff o2) -> o1.getName().compareTo(o2.getName()));

        return allBuffs.iterator();
    }
}
