package com.wecanteven.AreaView;

import com.sun.glass.ui.View;
import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.ViewObjects.Factories.BiomeFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.SimpleVOFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Abilities.HitBox;
import com.wecanteven.Models.Abilities.MovableHitBox;
import com.wecanteven.Models.Decals.Decal;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Entities.Mount;
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
import com.wecanteven.Models.Map.Aoe.*;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Visitors.*;

import java.util.Iterator;

/**
 * Created by alexs on 4/1/2016.
 */
public class VOCreationVisitor implements EntityVisitor, ItemVisitor, MapVisitor, TerrainVisitor, AreaOfEffectVisitor, WeaponsVisitor, DecalVisitor,MovableHitBoxVisitor,HitBoxVisitor {
    private SimpleVOFactory simpleVOFactory;
    private ViewObjectFactory viewObjectFactory;
    private AreaView areaView;
    private Biome biome;
    private BiomeFactory currentBiomeFactory;

    private boolean[][] foundTop;

    public VOCreationVisitor(AreaView areaView, SimpleVOFactory simpleVOFactory, ViewObjectFactory viewObjectFactory, Biome biome) {
        this.areaView = areaView;
        this.simpleVOFactory = simpleVOFactory;
        this.viewObjectFactory = viewObjectFactory;
        this.biome = biome;
        visitFutureObjects();
    }

    private Position currentPosition;
    private void visitFutureObjects(){
        HitBox.setVOCreationVisitor(this);
    }

    @Override
    public void visitHitBox(HitBox hitBox){
        areaView.addViewObject(viewObjectFactory.createHitBox(hitBox));

        //areaView.addViewObject(factory.createSimpleViewObject(hitBox.getLocation().toPosition(),"Decals/Cactus1.xml"));
    }
    @Override
    public void visitMovableHitBox(MovableHitBox hitBox){
        ViewObject mvo = viewObjectFactory.createRangedEffect(hitBox);

        areaView.addViewObject(mvo);
    }

    @Override
    public void visitEntity(Entity e) {

        //areaView.addViewObject(factory.createSneak(currentPosition, e.getDirection(), e));
    }

    @Override
    public void visitCharacter(Character c) {
        System.out.println("adding character to areaview");
        ViewObject avatar = viewObjectFactory.createBaseHominoid(currentPosition, c, "Connery");
        simpleVOFactory.makeLightSource(avatar, 5, c);
        simpleVOFactory.setCenter(avatar);
        areaView.addViewObject(avatar);
        areaView.setBackground(viewObjectFactory.createBackgroundDrawable(avatar));
        c.setFactory(simpleVOFactory);
    }

    @Override
    public void visitNPC(NPC c) {

        areaView.addViewObject(viewObjectFactory.createBaseHominoid(currentPosition, c,  "TestFace"));

    }

    @Override
    public void visitMount(Mount mount) {
        System.out.println("adding mount to areaview");
        ViewObject mountVO = viewObjectFactory.createBaseHominoid(currentPosition, mount, "mounty");
        mount.addVO(mountVO);
        mount.setFactory(simpleVOFactory);
        areaView.addViewObject(mountVO);

    }

    @Override
    public void visitItem(Item item) {

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        areaView.addViewObject(viewObjectFactory.createObstacle(currentPosition, obstacle));
    }

    @Override
    public void visitInteractiveItem(InteractiveItem interactable) {
        areaView.addViewObject(viewObjectFactory.createInteractableItem(currentPosition, interactable));
    }

    @Override
    public void visitOneShotItem(OneShot oneshot) {
        areaView.addViewObject(viewObjectFactory.createOneShotItem(currentPosition, oneshot));
    }

    @Override
    public void visitTakeableItem(TakeableItem takeable) {
        areaView.addViewObject(viewObjectFactory.createTakeableItem(takeable.getLocation().toPosition(), takeable));
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

        Iterator<AreaOfEffect> iter = tile.getAreasOfEffect();

        while (iter.hasNext()) {
            iter.next().accept(this);
        }
    }


    @Override
    public void visitWater(Water water) {
        areaView.addViewObject(viewObjectFactory.createWater(currentPosition));
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
        areaView.addViewObject(viewObjectFactory.createCurrent(currentPosition));
    }

    @Override
    public void visitAoe(AreaOfEffect aoe) { }

    @Override
    public void visitTickableAoe(TickableAreaOfEffect aoe) { }

    @Override
    public void visitTickableHealAoe(HealingAreaOfEffect aoe) {
        areaView.addViewObject(viewObjectFactory.createAoe(currentPosition, "HealAoe"));
    }

    @Override
    public void visitTickableTakeDamageAoe(TakeDamageAreaOfEffect aoe) {
        areaView.addViewObject(viewObjectFactory.createAoe(currentPosition, "DamageAoe"));
    }

    @Override
    public void visitOneTimeAoe(OneTimeAreaOfEffect aoe) { }

    @Override
    public void visitInstaDeathAoe(InstaDeathAoe aoe) {
        areaView.addViewObject(viewObjectFactory.createAoe(currentPosition, "InstaDeathAoe"));
    }

    @Override
    public void visitCoolDownAoe(CoolDownAoE aoe) { }

    @Override
    public void visitLevelUpAoe(LevelUpAoe aoe) {
        areaView.addViewObject(viewObjectFactory.createAoe(currentPosition, "LevelUpActive"));
    }

    @Override
    public void visitTeleportAoe(TeleportAoe aoe) {
        areaView.addViewObject(viewObjectFactory.createAoe(currentPosition, "TeleportAoe"));
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
    public void visitDecal(Decal d) {
        areaView.addViewObject(viewObjectFactory.createDecalViewObject(currentPosition, d));
    }
}
