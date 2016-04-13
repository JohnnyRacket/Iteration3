package com.wecanteven.AreaView.Biomes;

import com.wecanteven.AreaView.VOCreationVisitor;
import com.wecanteven.AreaView.ViewObjects.Factories.BiomeFactory;
import com.wecanteven.UtilityClasses.Location;

import java.util.ArrayList;

/**
 * Created by alexs on 4/13/2016.
 */
public class CustomBiome implements Biome{
    private BiomeFactory factory;
    private ArrayList<Location> locations;
    private Biome nextBiome;

    public CustomBiome(BiomeFactory factory, ArrayList<Location> locations, Biome nextBiome) {
        this.factory = factory;
        this.locations = locations;
        this.nextBiome = nextBiome;
    }

    @Override
    public void changeFactory(Location location, VOCreationVisitor creationVisitor) {
        if (hasLocation(location)) {
            creationVisitor.setBiomeFactory(factory);
        } else {
            nextBiome.changeFactory(location, creationVisitor);
        }
    }

    private boolean hasLocation(Location location) {
        for (Location myLocation: locations) {
            if (myLocation.equals(location)) return true;
        }
        return false;
    }

}
