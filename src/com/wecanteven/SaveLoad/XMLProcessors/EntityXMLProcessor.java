package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import org.w3c.dom.Attr;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class EntityXMLProcessor {

    public static void formatEntity(SaveFile save, Entity e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Height", e.getHeight()));
        save.appendObjectTo("Tile", save.createSaveElement("Entity",attr));
    }

    public static void parseEntity() {

    }

    public static void formatCharacter(SaveFile save, Character e, String parent) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("Occupation", e.getOccupation().getClass().getSimpleName()));
        attr.add(save.saveAttr("Height", e.getHeight()));
        save.appendObjectTo(parent, save.createSaveElement("Character",attr));
    }

    public static void formatAvatar(SaveFile save, Avatar avatar) {
        ArrayList<Attr> attr = new ArrayList<>();
        save.appendObjectTo("Tile", save.createSaveElement("Avatar", attr));
        formatCharacter(save, avatar.getCharacter(), "Avatar");
    }

    public static void formatStats(SaveFile save, Stats stats) {
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

    public static void formatDirection(SaveFile save, Direction direction) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("enum", direction.ordinal()));
        save.appendObjectTo("Character", save.createSaveElement("Direction",attr));

    }

}
