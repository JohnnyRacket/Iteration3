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

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public class TileXMLProcessor {
    private static  SaveFile sf;

    public static void formatMap(SaveFile save, Map map) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("r", map.getrSize()));
        attr.add(save.saveAttr("s", map.getsSize()));
        attr.add(save.saveAttr("z", map.getzSize()));

        save.appendMap(save.createSaveElement("Map",attr));

    }

    public static Map parseMap(Element el) {
        Map map = new Map(sf.getIntAttr(el, "r"), sf.getIntAttr(el, "s"), sf.getIntAttr(el, "z"));
        int width = map.getrSize();
        for(int r = 0; r < map.getrSize(); ++r) {
            System.out.println("r "+ r);
            for(int s = 0; s < map.getsSize(); ++s) {
                System.out.println(width*r + s);
                map.setColumn(r, s, parseColumn(map, sf.getElemenetById("Column", width*r + s)));
                if(map.getColumn(r, s) == null){ System.out.println("WTF COLUMN"); }
            }
        }
        return map;
    }


    public static void formatColumn(SaveFile save, Column column) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("z", column.getZ()));
        save.appendObjectTo("Map" ,save.createSaveElement("Column",attr));
    }

    public static Column parseColumn(Map map, Element el) {
        int z = sf.getIntAttr(el, "z");
        ArrayList<Tile> tiles = new ArrayList<>();
        for(int i = 0; i < z; ++i){
            tiles.add(parseTile(sf.getElemenetById(el, "Tile", i)));
        }
        return new Column(z, tiles);
    }


    public static void formatTile(SaveFile save, Tile tile) {
        ArrayList<Attr> attr = new ArrayList<>();
        attr.add(save.saveAttr("terrain", tile.getTerrain().getTerrain()));
        save.appendObjectTo("Column", save.createSaveElement("Tile",attr));

    }

    public static Tile parseTile(Element el){
        Terrain terrain;
        switch(sf.getStrAttr(el, "terrain")){
            case "Air":
                System.out.println("Making Air");
                terrain = new Air();
                break;
            case "Ground":
                System.out.println("Making Ground");
                terrain = new Ground();
                break;
            case "Current":
                System.out.println("Making Current");
                //TODO: Later support multiple current directions
                terrain = new Current(Direction.NORTHEAST);
                break;
            default:
                terrain = new Ground();
            break;
        }
        return new Tile(terrain);
    }


    public static void setCurrentSave(SaveFile currentSave) {
        TileXMLProcessor.sf = currentSave;
    }
}
