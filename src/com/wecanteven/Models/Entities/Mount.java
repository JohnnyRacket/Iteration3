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
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.Visitors.EntityVisitor;

/**
 * Created by adamfortier on 4/15/16.
 */
public class Mount extends Character {
  private Avatar mounter;
  private boolean mounted = false;


  public Mount(ActionHandler actionHandler, Direction direction) {
    super(actionHandler, direction, GameColor.BLUE);
  }


  public void mount(Avatar mounter) {
    this.mounter = mounter;
    //this.mounter.setDestroyed(true);
  }

  public void dismount() {
    mounter = null;
    setMounted(false);
  }

  public void accept(EntityVisitor visitor) {
      if(mounter != null) {
          mounter.getCharacter().accept(visitor);
      }
      visitor.visitMount(this);

  }


  @Override
  public void interact(Character character) {

    character.mount(this);
  }

  public void interact(Avatar avatar) {

    setMounted(true);
    avatar.mount(this);
  }

  @Override
  public void setLocation(Location location) {
    super.setLocation(location);
    if(mounter != null && !isDestroyed()) {
      mounter.getCharacter().setMovingTicks(getMovingTicks());
      mounter.getCharacter().setLocation(new Location(getLocation().getR(), getLocation().getS(), getLocation().getZ() + 2));
    }
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
