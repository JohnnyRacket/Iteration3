package com.wecanteven.AreaView.Biomes;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.UtilityClasses.Location;

/**
 * Created by alexs on 4/13/2016.
 */
public interface Biome {
    void changeFactory(Location location, VOCreationVisitor creationVisitor);
}
