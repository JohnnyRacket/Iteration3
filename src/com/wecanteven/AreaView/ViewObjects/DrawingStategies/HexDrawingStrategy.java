package com.wecanteven.AreaView.ViewObjects.DrawingStategies;


import com.wecanteven.AreaView.DynamicImages.DynamicImage;
import com.wecanteven.AreaView.Position;

import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class HexDrawingStrategy implements DynamicImageDrawingStrategy, StringDrawingStrategy {
    private final int HEX_WIDTH = 56;
    private final int HEX_LENGTH = 48;
    private final int HEX_HEIGHT = 15;


    private int findX(Position position) {
        return (int)(position.getR()*HEX_WIDTH);
    }

    private int findY(Position position) {
        return (int)(position.getS()*HEX_LENGTH + position.getR()*HEX_LENGTH/2 - position.getZ()*HEX_HEIGHT);
    }

    @Override
    public void draw(Graphics2D g, DynamicImage dImage, Position position) {
        g.drawImage(dImage.getImage(),
                findX(position) + dImage.getxOffset(),
                findY(position) + dImage.getyOffset(),
                null);
    }

    @Override
    public void draw(Graphics g, String text, Position position) {

    }
}
