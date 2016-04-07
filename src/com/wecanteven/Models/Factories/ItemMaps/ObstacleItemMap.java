package com.wecanteven.Models.Factories.ItemMaps;

import com.wecanteven.Models.Factories.ItemFactories.CreateCommands.IObstacleItemCreateCommand;
import com.wecanteven.Models.Factories.ItemFactories.ObstacleItemFactory;
import com.wecanteven.Models.Items.Obstacle;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class ObstacleItemMap {
    private static ObstacleItemMap instance;

    private HashMap<String, IObstacleItemCreateCommand> itemMap;

    private ObstacleItemMap() {
        itemMap = new HashMap<>();
        initialize();
    }

    public static ObstacleItemMap getInstance() {
        if (instance == null) {
            instance = new ObstacleItemMap();
        }

        return instance;
    }

    private void initialize() {
        itemMap.clear();

        //TODO put in the actual items
        itemMap.put("", () -> new ObstacleItemFactory().vendDefaultObstacleItem());
    }

    public Obstacle getItemAsObstacle(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name).create();
        }

        throw new IllegalArgumentException("There is no Obstacle with name: " + name);
    }

    /**
     * Extensibility
     * */

    protected void insertNewItem(String name, IObstacleItemCreateCommand newItemCommand) {
        itemMap.put(name, newItemCommand);
    }

    protected void removeItem(String name) {
        itemMap.remove(name);
    }
}
