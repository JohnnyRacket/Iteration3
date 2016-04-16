package com.wecanteven.Models.Entities.AvatarStates;

import com.wecanteven.UtilityClasses.Direction;

/**
 * Created by Alex on 4/14/2016.
 */
public class DeadState extends AvatarState {
    @Override
    public boolean move(Direction d) {
        return false;
    }

    @Override
    public void attack() {

    }

    @Override
    public void useAbility(int index) {

    }

    @Override
    public boolean equipItem(String id) {
        return false;
    }

    @Override
    public boolean unequipItem(String id) {
        return false;
    }

    @Override
    public boolean equipAbility(String id) {
        return false;
    }

    @Override
    public boolean unequipAbility(String id) {
        return false;
    }

    @Override
    public boolean consume(String id) {
        return false;
    }

    @Override
    public void drop() {

    }

    @Override
    public void pickup() {

    }

    @Override
    public void interactWith() {

    }

    @Override
    public void mount() {

    }

    @Override
    public void dismount() {

    }
}