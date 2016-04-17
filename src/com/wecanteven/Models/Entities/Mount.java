package com.wecanteven.Models.Entities;

import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by adamfortier on 4/15/16.
 */
public class Mount extends Character {
  Character mounter;
  ViewObject mountVO;

  public Mount(ActionHandler actionHandler, Direction direction) {
    super(actionHandler, direction, GameColor.BLUE);
  }


  public void mount(Character mounter) {
    this.mounter = mounter;
    notifyObserversOnNotDestroyed();
    try {
      getFactory().setCenter(mountVO);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    this.mounter.setDestroyed(true);
  }

  public void dismount() {

  }

  public void accept(EntityVisitor visitor) {
    visitor.visitMount(this);
  }

  public void addVO(ViewObject mountVO) {
    this.mountVO = mountVO;
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
