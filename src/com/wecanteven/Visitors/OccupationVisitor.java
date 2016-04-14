package com.wecanteven.Visitors;

import com.wecanteven.Models.Occupation.*;

/**
 * Created by John on 4/14/2016.
 */
public interface OccupationVisitor {
    void visitEnemy(Enemy enemy);
    void visitPet(Pet pet);
    void visitSmasher(Smasher smasher);
    void visitSneak(Sneak sneak);
    void visitSummoner(Summoner summoner);
}
