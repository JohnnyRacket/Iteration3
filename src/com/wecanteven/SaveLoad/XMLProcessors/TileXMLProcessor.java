package com.wecanteven.SaveLoad.XMLProcessors;

import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Terrain.Air;
import com.wecanteven.Models.Map.Terrain.Current;
import com.wecanteven.Models.Map.Terrain.Ground;
import com.wecanteven.Models.Map.Terrain.Terrain;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.SaveLoad.SaveFile;
import com.wecanteven.UtilityClasses.Direction;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
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
        Map map = new Map(sf.getIntAttr(el, "r"), sf.getIntAttr(el, "s"), sf.getIntAttr(el, "z"));
        map.setName(sf.getStrAttr(el, "name"));
        int width = map.getrSize();
        for(int r = 0; r < map.getrSize(); ++r) {
            for(int s = 0; s < map.getsSize(); ++s) {
                map.setColumn(r, s, parseColumn(map, sf.getElemenetById("Column", width*r + s)));
                if(map.getColumn(r, s) == null){
                    System.out.println("Error Column Parsing");
                }
            }
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


    public static void formatTile(Tile tile) {
        ArrayList<Attr> attr = new ArrayList<>();
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
        return t;
    }

    public static void formatCurrentDirection(Terrain terrain) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(sf.saveAttr("enum", ((Current)terrain).getDirection().ordinal()));
        sf.appendObjectToMostRecent(sf.createSaveElement("CurrentDirection",attr));
    }

}
