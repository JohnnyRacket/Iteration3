package com.wecanteven.SaveLoad.SaveVisitors;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Occurs;
import com.wecanteven.Models.Entities.Avatar;
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
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.Visitors.*;
import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class XMLSaveVisitor implements MapVisitor, ColumnVisitor, AvatarVisitor, EntityVisitor, StatsVisitor, ItemVisitor{

    SaveFile save;
    //this is hacky as hell - @TODO: Fix this later
    private String avatarsCharacter;

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
        if(tile.hasObstacle()) {tile.getObstacle().accept(this);}
        if(tile.hasInteractiveItem()) {tile.getInteractiveItem().accept(this);}
        if(tile.hasOneShot()) {tile.getOneShot().accept(this);}
        if(!tile.getTakeableItems().isEmpty()){
            for (TakeableItem i: tile.getTakeableItems()) {
                i.accept(this);
            }
        }

    }

    @Override
    public void visitAvatar(Avatar e) {
        //avatarsCharacter = e.getClass().get.toString();
    }

    @Override
    public void visitEntity(Entity e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Height", e.getHeight()));
        save.appendObjectTo("Tile", save.createSaveElement("Entity",attr));
        saveDirection(e.getDirection());



    }

    @Override
    public void visitCharacter(Character e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Occupation", e.getOccupation().getClass().getSimpleName()));
        attr.add(save.saveAttr("Height", e.getHeight()));
        System.out.println(avatarsCharacter);
        System.out.println(e.getClass().toString());
        if(avatarsCharacter == e.getClass().toString()){
            System.out.println(avatarsCharacter);
            System.out.println(e.getClass().toString());
            attr.add(save.saveAttr("Avatar", "true"));
        }
        save.appendObjectTo("Tile", save.createSaveElement("Character",attr));
        saveDirection(e.getDirection());
        visitStats(e.getStats());
    }


    @Override
    public void visitStats(Stats stats) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Lives", stats.getLives()));
        attr.add(save.saveAttr("Level", stats.getLevel()));
        attr.add(save.saveAttr("Strength", stats.getStrength()));
        attr.add(save.saveAttr("Agility", stats.getAgility()));
        attr.add(save.saveAttr("Intellect", stats.getIntellect()));
        attr.add(save.saveAttr("Hardiness", stats.getHardiness()));
        attr.add(save.saveAttr("Movement", stats.getMovement()));

        save.appendObjectTo("Character", save.createSaveElement("Stats",attr));
    }

    @Override
    public void visitItem(Item item) {

    }

    @Override
    public void visitObstacle(Obstacle item) {

    }

    @Override
    public void visitInteractiveItem(InteractiveItem item) {

    }

    @Override
    public void visitOneShotItem(OneShot item) {

    }

    @Override
    public void visitTakeableItem(TakeableItem item) {

    }

    @Override
    public void visitEquipableItem(EquipableItem item) {

    }

    @Override
    public void visitUseableItem(UseableItem item) {

    }

    @Override
    public void visitAbilityItem(AbilityItem item) {

    }

    @Override
    public void visitConsumableItem(ConsumeableItem item) {

    }

    public void saveDirection(Direction direction){
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("enum", direction.ordinal()));
        save.appendObjectTo("Character", save.createSaveElement("Direction",attr));
    }

}

