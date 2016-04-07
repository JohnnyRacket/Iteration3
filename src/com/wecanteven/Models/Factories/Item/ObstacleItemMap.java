package com.wecanteven.Models.Factories.Item;

import com.wecanteven.Models.Items.Obstacle;
import com.wecanteven.Models.Items.OneShot;
import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.HashMap;

/**
 * Created by simonnea on 4/6/16.
 */
public class ObstacleItemMap {
    private static ObstacleItemMap instance;

    private HashMap<String, Obstacle> itemMap;

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

        itemMap.put("", new Obstacle(""));
    }

    public Obstacle getItemAsObstacle(String name) {
        if (itemMap.containsKey(name)) {
            return itemMap.get(name);
        }

        throw new IllegalArgumentException("There is no Obstacle with name: " + name);
    }
}
