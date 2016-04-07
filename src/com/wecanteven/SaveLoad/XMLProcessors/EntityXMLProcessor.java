package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Entities.*;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Occupation.Occupation;
import com.wecanteven.Models.Occupation.Smasher;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import sun.print.SunMinMaxPage;

import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class EntityXMLProcessor extends XMLProcessor {

    public static void formatEntity(Entity e) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("Height", e.getHeight()));
        sf.appendObjectTo("Tile", sf.createSaveElement("Entity",attr));
        formatLocation(sf, e, "Entity");

    }

    public static void parseEntity(Map map, Element el) {

    }

    public static void formatCharacter(Character e, String parent) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("Occupation", e.getOccupation().getClass().getSimpleName()));
        attr.add(sf.saveAttr("Height", e.getHeight()));
        sf.appendObjectTo(parent, sf.createSaveElement("Character",attr));
        formatLocation(sf, e, "Character");
    }

    public static Character parseCharacter(Map map, Element el) {
        Character c =  new Character(map, parseDirection(sf.getElemenetById(el, "Direction", 0)), parseOccupation(sf.getStrAttr(el, "Occupation")), StorageXMLProcessor.parseItemStorage());
        parseStats(c, sf.getElemenetById(el, "Stats", 0));
        StorageXMLProcessor.parseItemStorage();
        map.add(c, parseLocation(sf.getElemenetById(el, "Location", 0)));
        return c;
    }

    public static void formatAvatar(Avatar avatar) {
        ArrayList<Attr> attr = new ArrayList<>();
        sf.appendObjectTo("Tile", sf.createSaveElement("Avatar", attr));
        formatCharacter(avatar.getCharacter(), "Avatar");
    }

    public static Avatar parseAvatar(Map map, Element el) {
        return new Avatar(parseCharacter(map, sf.getElemenetById(el, "Character", 0)), map);
    }
    public static void formatStats(Stats stats) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("lives", stats.getLives()));
        attr.add(sf.saveAttr("level", stats.getLevel()));
        attr.add(sf.saveAttr("strength", stats.getStrength()));
        attr.add(sf.saveAttr("agility", stats.getAgility()));
        attr.add(sf.saveAttr("intellect", stats.getIntellect()));
        attr.add(sf.saveAttr("hardiness", stats.getHardiness()));
        attr.add(sf.saveAttr("movement", stats.getMovement()));

        sf.appendObjectTo("Character", sf.createSaveElement("Stats",attr));
    }

    public static void parseStats(Entity e, Element el) {
        e.getStats().initStats(e,
                sf.getIntAttr(el, "strength"),
                sf.getIntAttr(el, "agility"),
                sf.getIntAttr(el, "intellect"),
                sf.getIntAttr(el, "hardiness"),
                sf.getIntAttr(el, "movement"),
                sf.getIntAttr(el, "lives"),
                sf.getIntAttr(el, "level")
        );
    }


    public static void formatLocation(SaveFile save, Entity e, String parent) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("r", e.getLocation().getR()));
        attr.add(save.saveAttr("s", e.getLocation().getS()));
        attr.add(save.saveAttr("z", e.getLocation().getZ()));
        save.appendObjectTo(parent, save.createSaveElement("Location",attr));
    }

    public static Location parseLocation(Element el){
        return new Location(sf.getIntAttr(el,"r"),sf.getIntAttr(el, "s"), sf.getIntAttr(el, "z"));
    }

    public static void formatDirection(Direction direction) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("enum", direction.ordinal()));
        sf.appendObjectTo("Character", sf.createSaveElement("Direction",attr));

    }

    public static Direction parseDirection(Element el) {
        return Direction.values()[sf.getIntAttr(el, "enum")];
    }

    public static Occupation parseOccupation(String o) {
        switch(o){
            case "Smasher":
                return new Smasher();
            case "Summoner":
                //new Summoner();
                return null;
            case "Sneak":
                //new Sneak();
                return null;
            default:
                return new Smasher();
        }
    }


}
