package com.wecanteven.AreaView.ViewObjects.DrawingStategies;


import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.UtilityClasses.Config;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class HexDrawingStrategy implements DynamicImageDrawingStrategy, StringDrawingStrategy {
    public static final int HEX_WIDTH = 56;
    public static final int HEX_LENGTH = 48;
    public static final int HEX_HEIGHT = 15;
    private ViewObject centerTarget;

    public HexDrawingStrategy() {}

    public void setCenterTarget(ViewObject centerTarget) {
        this.centerTarget = centerTarget;
    }

    private int findX(Position position) {
        return (int)(position.getR()*HEX_WIDTH);
    }

    private int findY(Position position) {
        return (int)(position.getS()*HEX_LENGTH + position.getR()*HEX_LENGTH/2 - position.getZ()*HEX_HEIGHT);
    }

    @Override
    public void draw(Graphics2D g, DynamicImage dImage, Position position) {
        g.drawImage(dImage.getImage(),
                findX(position) + dImage.getxOffset() - findX(centerTarget.getPosition()) + Config.SCREEN_WIDTH/2,
                findY(position) + dImage.getyOffset() - findY(centerTarget.getPosition()) + Config.SCREEN_HEIGHT/2,
                null);

    }

    @Override
    public void draw(Graphics g, String text, Position position) {

    }
}
