package com.wecanteven.AreaView;

import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.ViewObjects.Factories.BiomeFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.PlainsFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Decals.FlowerDecal;
import com.wecanteven.Models.Decals.GroundDecal;
import com.wecanteven.Models.Decals.TreeDecal;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.NPC;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.AbilityItem;
import com.wecanteven.Models.Items.Takeable.ConsumeableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.*;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.UseableItem;
import com.wecanteven.Models.Map.Aoe.AreaOfEffect;
import com.wecanteven.Models.Map.Aoe.HealingAreaOfEffect;
import com.wecanteven.Models.Map.Aoe.TickableAreaOfEffect;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Visitors.*;

/**
 * Created by alexs on 4/1/2016.
 */
public class VOCreationVisitor implements EntityVisitor, ItemVisitor, MapVisitor, TerrainVisitor, AreaOfEffectVisitor, WeaponsVisitor, DecalVisitor {
    private ViewObjectFactory factory;
    private AreaView areaView;
    private Biome biome;
    private BiomeFactory currentBiomeFactory;

    private boolean[][] foundTop;

    public VOCreationVisitor(AreaView areaView, ViewObjectFactory factory, Biome biome) {
        this.areaView = areaView;
        this.factory = factory;
        this.biome = biome;
    }

    private Position currentPosition;


    @Override
    public void visitEntity(Entity e) {
        System.out.println("adding entity to areaview");
        //areaView.addViewObject(factory.createSneak(currentPosition, e.getDirection(), e));
    }

    @Override
    public void visitCharacter(Character c) {
        System.out.println("adding character to areaview");
        ViewObject avatar = factory.createSneak(currentPosition, c.getDirection(), c);
        areaView.addViewObject(avatar);
        areaView.setBackground(factory.createBackgroundDrawable(avatar));

    }

    @Override
    public void visitNPC(NPC c) {
        System.out.println("adding character to areaview");
        areaView.addViewObject(factory.createSneak(currentPosition, c.getDirection(), c));

    }

    @Override
    public void visitItem(Item item) {

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        areaView.addViewObject(factory.createObstacle(currentPosition, obstacle));
    }

    @Override
    public void visitInteractiveItem(InteractiveItem interactable) {
        areaView.addViewObject(factory.createInteractableItem(currentPosition, interactable));
    }

    @Override
    public void visitOneShotItem(OneShot oneshot) {
        areaView.addViewObject(factory.createOneShotItem(currentPosition, oneshot));
    }

    @Override
    public void visitTakeableItem(TakeableItem takeable) {
        areaView.addViewObject(factory.createTakeableItem(currentPosition, takeable));
    }

    @Override
    public void visitEquipableItem(EquipableItem equipable) {
        visitTakeableItem(equipable);
    }

    @Override
    public void visitUseableItem(UseableItem useable) {

    }

    @Override
    public void visitAbilityItem(AbilityItem ability) {

    }

    @Override
    public void visitConsumableItem(ConsumeableItem consumable) {

    }

    public void setBiomeFactory(BiomeFactory currentBiomeFactory) {
        this.currentBiomeFactory = currentBiomeFactory;
    }

    @Override
    public void visitMap(Map map) {

        foundTop = new boolean[map.getrSize()][map.getsSize()];

        for (int i = 0; i < map.getrSize(); i++) {
            for (int j = 0; j < map.getsSize(); j++) {
                foundTop[i][j] = false;
                for (int k = map.getzSize() - 1; k >= 0; k--) {
                    this.currentPosition = new Position(i, j, k);
                    biome.changeFactory(currentPosition.getLocation(), this);
                    map.getTile(i, j, k).accept(this);
                }
            }
        }
    }



    @Override
    public void visitTile(Tile tile) {
        if (tile.hasEntity() ) {
            tile.getEntity().accept(this);
        }
        if (tile.hasInteractiveItem() ) tile.getInteractiveItem().accept(this);
        if (tile.hasObstacle() ) tile.getObstacle().accept(this);
        if (tile.hasOneShot() ) tile.getOneShot().accept(this);

        tile.getTerrain().accept(this);

        for (TakeableItem takeableItem: tile.getTakeableItems()) {
            takeableItem.accept(this);
        }

        for (Decal decal: tile.getDecals()) {
            decal.accept(this);
        }

        if (tile.hasAoe()) {
            tile.acceptAoeVisitor(this);
        }
    }


    @Override
    public void visitWater(Water water) {
        areaView.addViewObject(factory.createWater(currentPosition));
    }

    @Override
    public void visitGround(Ground ground) {
        int r = currentPosition.getLocation().getR();
        int s = currentPosition.getLocation().getS();


        areaView.addViewObject( foundTop[r][s] ? currentBiomeFactory.createBelowGround(currentPosition) : currentBiomeFactory.createAboveGround(currentPosition));
        foundTop[r][s] = true;
    }

    @Override
    public void visitAir(Air air) {

    }

    @Override
    public void visitCurrent(Current current) {
        areaView.addViewObject(factory.createWater(currentPosition));
    }


    @Override
    public void visitAoe(AreaOfEffect aoe) { }

    @Override
    public void visitTickableAoe(TickableAreaOfEffect aoe) {

    }

    @Override
    public void visitTickableHealAoe(HealingAreaOfEffect aoe) {
        areaView.addViewObject(factory.createAoe(currentPosition));
    }

    @Override
    public void visitOneHandedWeapon(OneHandedWeapon oneHandedWeapon) {

    }

    @Override
    public void visitDualWieldWeapon() {

    }

    @Override
    public void visitDualWieldMeleeWeapon(DualWieldMeleeWeapon dualWieldMeleeWeapon) {

    }

    public void visitOneHandedMeleeWeapon(OneHandedMeleeWeapon oneHandedMeleeWeapon) {
        visitTakeableItem(oneHandedMeleeWeapon);
    }

    @Override
    public void visitOneHandedRangedWeapon(OneHandedRangedWeapon oneHandedRangedWeapon) {
        visitTakeableItem(oneHandedRangedWeapon);
    }

    @Override
    public void visitWeapon(WeaponEquipableItem weapon) {
        visitTakeableItem(weapon);
    }

    @Override
    public void visitFlowerDecal(FlowerDecal f) {

    }

    @Override
    public void visitGroundDecal(GroundDecal g) {

    }

    @Override
    public void visitTreeDecal(TreeDecal t) {
        areaView.addViewObject(currentBiomeFactory.createTreeDecal(currentPosition, t));
    }
}
