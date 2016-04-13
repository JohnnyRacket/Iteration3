package com.wecanteven.AreaView.Biomes;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.AreaView.ViewObjects.Factories.BiomeFactory;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;

/**
 * Created by alexs on 4/13/2016.
 */
public class DefaultBiome implements Biome {
    private BiomeFactory factory;

    public DefaultBiome(BiomeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void changeFactory(Location location, VOCreationVisitor creationVisitor) {
        creationVisitor.setBiomeFactory(factory);
    }
}
