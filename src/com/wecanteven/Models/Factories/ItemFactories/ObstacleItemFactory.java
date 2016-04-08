package com.wecanteven.Models.Factories.ItemFactories;

import com.wecanteven.Models.Items.Obstacle;

/**
 * Created by Cachorrita on 4/6/2016.
 */
public class ObstacleItemFactory {
    public Obstacle vendObstacleItem(String name) {
        return new Obstacle(name);
    }
}
