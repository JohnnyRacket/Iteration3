package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import java.awt.*;

/**
 * Created by John on 4/7/2016.
 */
public class SplitTextScrollableItem extends LeftAlignScrollableMenuItem {

    private String rightString;
    public SplitTextScrollableItem(String leftString, String rightString, SelectableMenuItemCommand command) {
        super(leftString, command);
        this.rightString = rightString;
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {
        super.draw(g2d, x, y, windowWidth, windowHeight);
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.drawString(this.getRightString(),x + windowWidth - metrics.stringWidth(this.getRightString()), y + windowHeight/2 + metrics.getAscent()/2);
    }

    public String getRightString() {
        return rightString;
    }

    public void setRightString(String rightString) {
        this.rightString = rightString;
    }
}
