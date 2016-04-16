package com.wecanteven.UtilityClasses;

import java.awt.*;

/**
 * Created by Alex on 4/16/2016.
 */
public enum GameColor {
    /*
    CHARACTER NAMES
        Periwinkle
        Marigold
        Sherbet
        Shamrock
    */

    BLUE(0x8db5e7, 0x7293bb, "Blue", "Periwinkle"),
    PINK(0xf19cb7, 0xe087a2, "Pink", "Sherbet"),
    YELLOW(0xffcc00, 0xefbf00, "Yellow", "Marigold"),
    GREEN(0x6fc4a9, 0x55ae92, "Green", "Shamrock"),
    GRAY(0x9f9f9f, 0x6f6f6f, "Gray", "Ash");

    public final Color light;
    public final String name;
    public final Color dark;

    GameColor(int lightColorCode, int darkColorCode, String standardName, String race) {
        this.light = new Color(lightColorCode);
        this.name = standardName;
        this.dark = new Color(darkColorCode);
    }
    @Override
    public String toString() {
        return name;
    }
}
