package com.wecanteven.SaveLoad.SaveVisitors;

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
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.Visitors.ColumnVisitor;
import com.wecanteven.Visitors.EntityVisitor;
import com.wecanteven.Visitors.ItemVisitor;
import com.wecanteven.Visitors.MapVisitor;
import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class XMLSaveVisitor implements MapVisitor, ColumnVisitor, EntityVisitor, ItemVisitor{

    SaveFile save;

    public XMLSaveVisitor(SaveFile save) {
        this.save = save;
    }

    @Override
    public void visitMap(Map map) {

        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("r", map.getrSize()));
        attr.add(save.saveAttr("s", map.getsSize()));
        save.appendMap(save.createSaveElement("Map",attr));

        for(int r = 0; r < map.getrSize(); ++r){
            for(int s = 0; s < map.getsSize(); ++s){
                map.getColumn(r, s).accept(this);
            }
        }
    }

    public void visitColumn(Map.Column column) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("z", column.getZ()));
        save.appendObjectTo("Map" ,save.createSaveElement("Column",attr));

        for(int z  = 0; z < column.getZ(); ++z){
            column.getTile(z).accept(this);
        }

    }
    @Override
    public void visitTile(Tile tile) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("terrain", tile.getTerrain().getTerrain()));
        save.appendObjectTo("Column", save.createSaveElement("Tile",attr));

        if(tile.hasEntity()){tile.getEntity().accept(this);}
    }

    @Override
    public void visitEntity(Entity e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Height", e.getHeight()));
        save.appendObjectTo("Tile", save.createSaveElement("Entity",attr));
        saveDirection(e.getDirection());



    }

    @Override
    public void visitCharacter(Character c) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Height", c.getHeight()));
        save.appendObjectTo("Tile", save.createSaveElement("Character",attr));
        saveDirection(c.getDirection());
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

    public void saveDirection(Direction direction){
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("enum", direction.ordinal()));
        save.appendObjectTo("Character", save.createSaveElement("Direction",attr));
    }
}

