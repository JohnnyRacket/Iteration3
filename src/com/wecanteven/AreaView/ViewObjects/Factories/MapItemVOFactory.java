package com.wecanteven.AreaView.ViewObjects.Factories;

import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.DynamicImages.StartableDynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DecalViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.DestroyableViewObject;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MicroPositionableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.ActivatableViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.StartableViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Storage.EquipmentSlots.EquipmentSlot;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.GameColor;

/**
 * Created by adamfortier on 4/17/16.
 */
public class MapItemVOFactory {
    private SimpleVOFactory simpleVOFactory;
    private DynamicImageFactory dynamicImageFactory = DynamicImageFactory.getInstance();


    public MapItemVOFactory(SimpleVOFactory simpleVOFactory) {
        this.simpleVOFactory = simpleVOFactory;
    }

    public SimpleViewObject createAoe(Position position, String name) {
        return simpleVOFactory.createSimpleViewObject(position, "AreaOfEffects/" + name + ".xml");
    }


    public ViewObject createInteractableItem(Position position, InteractiveItem interactiveItem) {
        ActivatableViewObject activatableViewObject = simpleVOFactory.createActivatableViewObject(position, interactiveItem);
        interactiveItem.attach(activatableViewObject);
        return activatableViewObject;
    }

    public SimpleViewObject createObstacle(Position position, Obstacle obstacle) {
        return simpleVOFactory.createSimpleViewObject(position, "Items/" + obstacle.getName() + "/" + obstacle.getName() + ".xml");
    }

    public DecalViewObject createDecalViewObject(Position position, Decal decal) {
        return new DecalViewObject(
                simpleVOFactory.createSimpleViewObject(position, "Decals/" + decal.getName() + ".xml"),
                decal.getR(),
                decal.getS()
        );
    }

    public DestroyableViewObject createOneShotItem(Position position, OneShot oneShot) {
        StartableDynamicImage animation = dynamicImageFactory.loadActiveDynamicImage("Items/" + oneShot.getName() + "/" + oneShot.getName() + ".xml");

        return simpleVOFactory.createOneShotItem(position, animation, oneShot);
    }


    public ViewObject createHitBox(HitBox hitBox) {
        String path = "Effects/" + hitBox.getName() + "/" + hitBox.getName() + ".xml";
        Position p = hitBox.getLocation().toPosition();
        StartableViewObject hitBoxVO = simpleVOFactory.createStartableViewObject(p, path);
        hitBoxVO.start(hitBox.getDuration());
        return hitBoxVO;
    }

    public DestroyableViewObject createTakableItem(Position position, TakeableItem takeableItem) {
        String path = "Items/" + takeableItem.getName() + "/" + takeableItem.getName() + ".xml";
        return simpleVOFactory.createDestroyableViewObject(simpleVOFactory.createSimpleViewObject(position, path),
                                                            simpleVOFactory.createStartableViewObject(position, path), takeableItem, 1);
    }

}
