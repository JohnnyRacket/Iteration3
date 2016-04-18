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
  private Character mounter;
  private boolean mounted = false;


  public Mount(ActionHandler actionHandler, Direction direction) {
    super(actionHandler, direction, GameColor.BLUE);
  }


  public void mount(Character mounter) {
    this.mounter = mounter;
    //this.mounter.setDestroyed(true);
  }

  public void dismount() {
    mounter = null;
    setMounted(false);
  }

  public void accept(EntityVisitor visitor) {
      visitor.visitMount(this);
      if(mounter != null) {
          visitor.visitCharacter(mounter);
      }
  }


  @Override
  public void interact(Character character) {

    character.mount(this);
  }

  public void interact(Avatar avatar) {

    setMounted(true);
    avatar.mount(this);
  }



  private void setMounted(boolean mounted) {
    this.mounted = mounted;
    notifyObservers();
  }

  public boolean getMounted() {
    return mounted;
  }
  public String toString() {
    return "Mount instance!";
  }



}
