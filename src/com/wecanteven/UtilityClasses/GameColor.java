package com.wecanteven.UtilityClasses;

import java.awt.*;

/**
 * Created by Alex on 4/16/2016.
 */
public enum GameColor {
    BLUE(0x8db5e7, 0x7293bb, "Blue"),
    PINK(0xf19cb7, 0xe087a2, "Pink"),
    YELLOW(0xffcc00, 0xefbf00, "Yellow"),
    GREEN(0x6fc47d, 0x4b9a58, "Green"),
    GRAY(0x9f9f9f, 0x6f6f6f, "Gray");

    public final Color light;
    public final String name;
    public final Color dark;

    GameColor(int lightColorCode, int darkColorCode, String standardName) {
        this.light = new Color(lightColorCode);
        this.name = standardName;
        this.dark = new Color(darkColorCode);
    }
    @Override
    public String toString() {
        return name;
    }
}
