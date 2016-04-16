package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.GameLaunching.LevelFactories.LevelFactoryFactory;
import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.*;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class TileXMLProcessor extends XMLProcessor {

    public static void formatMap(Map map) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("name", map.getName()));
        attr.add(sf.saveAttr("r", map.getrSize()));
        attr.add(sf.saveAttr("s", map.getsSize()));
        attr.add(sf.saveAttr("z", map.getzSize()));

        sf.appendMap(sf.createSaveElement("Map",attr));

    }

    public static Map parseMap(Element el) {
        Map map = (new LevelFactoryFactory().vendLevelFactory(sf.getStrAttr(el, "name"))).createMap();
        map.setName(sf.getStrAttr(el, "name"));
        NodeList tiles = sf.getElementsById("Tile");
        for(int i = 0; i < tiles.getLength(); ++i) {
            Element tile =  (Element)tiles.item(i);
            System.out.println("r:" + sf.getIntAttr(tile, "r")  + " s:" + sf.getIntAttr(tile, "s")  + "  z:" + sf.getIntAttr(tile, "z"));
            Column column = map.getColumn(sf.getIntAttr(tile, "r"), sf.getIntAttr(tile, "s"));
            column.setTile(parseTile(map, tile), sf.getIntAttr(tile, "z"));
        }
        return map;
    }


    public static void formatColumn(Column column) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("z", column.getZ()));
        sf.appendObjectTo("Map" ,sf.createSaveElement("Column",attr));
    }

    public static Column parseColumn(Map map, Element el) {
        int z = sf.getIntAttr(el, "z");
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int i = 0; i < z; ++i){
            tiles.add(parseTile(map, sf.getElemenetById(el, "Tile", i)));
        }
        return new Column(z, tiles);
    }


    public static void formatTile(Tile tile, Location location) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("r", location.getR()));
        attr.add(sf.saveAttr("s", location.getS()));
        attr.add(sf.saveAttr("z", location.getZ()));

        attr.add(sf.saveAttr("terrain", tile.getTerrain().getTerrain()));
        sf.appendObjectTo("Column", sf.createSaveElement("Tile",attr));
        if(tile.getTerrain().getTerrain() == "Current"){
            formatCurrentDirection(tile.getTerrain());
        }
    }




    public static Tile parseTile(Map map, Element el){
        Terrain terrain;
        switch(sf.getStrAttr(el, "terrain")){
            case "Air":
                //System.out.println("Making Air");
                terrain = new Air();
                break;
            case "Ground":
                //System.out.println("Making Ground");
                terrain = new Ground();
                break;
            case "Current":
                terrain = new Current(EntityXMLProcessor.parseDirection(sf.getElemenetById(el, "CurrentDirection", 0)));
                break;
            case "Water":
                terrain = new Water();
                break;
            default:
                terrain = new Ground();
            break;
        }
        Tile t = new Tile(terrain);
        Element obstacle = sf.getElemenetById(el, "Obstacle", 0);
        if(obstacle != null){
            t.add(ItemXMLProcessor.parseObstacleItem(obstacle));
        }
        Element Interactive = sf.getElemenetById(el, "InteractiveItem", 0);
        if(Interactive != null){
            t.add(ItemXMLProcessor.parseInteractiveItem(Interactive));
        }
        Element OneShot = sf.getElemenetById(el, "OneShot", 0);
        if(OneShot != null){
            t.add(ItemXMLProcessor.parseOneShotItem(OneShot));
        }
        Element NPC  = sf.getElemenetById(el, "NPC", 0);
        if(NPC != null) {
            t.add(EntityXMLProcessor.parseNPC(map, NPC));
        }

        NodeList aoes = sf.getElementsById(el, "AreaOfEffect");
        for (int i = 0; i < aoes.getLength(); i++) {
            Node cur = aoes.item(i);

            t.add(AOEXMLProcessor.parseAoe((Element)cur));
        }

        return t;
    }

    public static void formatCurrentDirection(Terrain terrain) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("enum", ((Current)terrain).getDirection().ordinal()));
        sf.appendObjectToMostRecent(sf.createSaveElement("CurrentDirection",attr));
    }

}
