package com.wecanteven.Models.Entities;

import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Models.BuffManager.Buff;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Models.Stats.StatsAddable;
import com.wecanteven.Observers.Observer;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.CanFallVisitor;
import com.wecanteven.Visitors.CanMoveVisitor;
import com.wecanteven.Visitors.EntityVisitor;

import java.util.ArrayList;

/**
 * Created by adamfortier on 4/15/16.
 */
public class Mount extends Character {
  Character mountee;

  public Mount(ActionHandler actionHandler, Direction direction) {
    super(actionHandler, direction, GameColor.BLUE);
  }


  public void mount(Character mountee) {
    this.mountee = mountee;
  }

  public void dismount() {

  }

  @Override
  public void interact(Character character) {
    System.out.println("MOUNT INTERACTION METHOD");
    character.mount(this);
  }

  public void interact(Avatar avatar) {
    System.out.println("Interacting with Avatar");
    avatar.mount(this);
  }

  public String toString() {
    return "Mount instance!";
  }
}
