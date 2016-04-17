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



  public Mount(ActionHandler actionHandler, Direction direction) {
    super(actionHandler, direction, GameColor.BLUE);
  }


  public void mount(Character mounter) {
    this.mounter = mounter;
    this.mounter.setDestroyed(true);
  }

  public void dismount() {
    mounter = null;
  }

  public void accept(EntityVisitor visitor) {
      visitor.visitMount(this);
      if(mounter != null) {
          visitor.visitCharacter(mounter);
      }
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
