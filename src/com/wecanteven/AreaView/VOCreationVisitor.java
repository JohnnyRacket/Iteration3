package com.wecanteven.AreaView;

import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Items.Item;
import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.AbilityItem;
import com.wecanteven.Models.Items.Takeable.ConsumeableItem;
import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Models.Items.Takeable.TakeableItem;
import com.wecanteven.Models.Items.Takeable.UseableItem;
import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Water;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.Models.Map.TileSlot;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.ItemVisitor;
import com.wecanteven.Visitors.MapVisitor;
import com.wecanteven.Visitors.TerrainVisitor;

/**
 * Created by alexs on 4/1/2016.
 */
public class VOCreationVisitor implements EntityVisitor, ItemVisitor, MapVisitor, TerrainVisitor{
    @Override
    public void visitEntity(Entity e) {

    }

    @Override
    public void visitCharacter(Character c) {

    }

    @Override
    public void visitItem(Item item) {

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {

    }

    @Override
    public void visitInteractiveItem(InteractiveItem interactable) {

    }

    @Override
    public void visitOneShotItem(OneShot oneshot) {

    }

    @Override
    public void visitTakeableItem(TakeableItem takeable) {

    }

    @Override
    public void visitEquipableItem(EquipableItem equipable) {

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

    @Override
    public void visitMap(Map map) {

    }

    @Override
    public void visitColumn(Column column) {

    }

    @Override
    public void visitTile(Tile tile) {

    }

    @Override
    public void visitTileSlot(TileSlot tileSlot) {

    }

    @Override
    public void visitWater(Water water) {

    }

    @Override
    public void visitGround(Ground ground) {

    }

    @Override
    public void visitAir(Air air) {

    }

    @Override
    public void visitCurrent(Current current) {

    }
}
