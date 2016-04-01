package com.wecanteven.Models;

/**
 * Created by John on 3/31/2016.
 */
public class ModelTime implements Tickable {
    private static ModelTime ourInstance = new ModelTime();

    public static ModelTime getInstance() {
        return ourInstance;
    }

    private ModelTime() {
    }

    @Override
    public void tick() {

    }
}
