package com.wecanteven.Models.Entities;

import com.wecanteven.AreaView.ViewObjects.Factories.SimpleVOFactory;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.ActionHandler;
import com.wecanteven.Observers.Activatable;
import com.wecanteven.Observers.Directional;
import com.wecanteven.Observers.Positionable;
import com.wecanteven.Observers.ViewObservable;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by adamfortier on 4/15/16.
 */
public class Mount extends Character {
  Character mounter;
  ViewObject mountVO;
  private boolean mounted = false;
  //SimpleVOFactory simpleVOFactory;

  public Mount(ActionHandler actionHandler, Direction direction) {
    super(actionHandler, direction, GameColor.BLUE);
   // this.simpleVOFactory = simpleVOFactory;
  }


  public void mount(Character mounter) {
    this.mounter = mounter;
    this.mounted = true;
//    try {
//      getFactory().setCenter(mountVO);
//    }
//    catch(Exception e) {
//      e.printStackTrace();
//    }
    this.mounter.setDestroyed(true);
  }

  public void dismount() {
    mounted = false;
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

//  public <T extends Positionable & ViewObservable> void setActiveView(int radius) {
//    try {
//      getFactory().makeLightSource(this, radius);
//    }
//    catch(Exception e) {
//      e.printStackTrace();
//    }
//  }

  public String toString() {
    return "Mount instance!";
  }

}
