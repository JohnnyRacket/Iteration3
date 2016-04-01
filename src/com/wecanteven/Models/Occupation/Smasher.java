package com.wecanteven.Models.Occupation;

import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Visitors.TerranianCanMoveVisitor;

/**
 * Created by Brandon on 3/31/2016.
 */
public class Smasher extends Occupation{
    public Smasher(){
        this.setCanMoveVisitor(new TerranianCanMoveVisitor());
        //TODO: add stats addable stuff when brandon pushes it
    }
}
