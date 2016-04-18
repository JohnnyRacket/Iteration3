package com.wecanteven.Models.Abilities.Effects;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Character;

/**
 * Created by Brandon on 4/17/2016.
 */
public class InteractionEffect implements Effects {
    private Character caster;
    private int skillLevel;

    public InteractionEffect(Character caster, int skillLevel){
        this.caster = caster;
        this.skillLevel = skillLevel;
    }

    @Override
    public void interact(Entity entity) {
        ViewTime.getInstance().register(()->{

            UIViewFactory.getInstance().createPickPocketView(caster,(Character)entity,skillLevel);
        },1);
    }
}
