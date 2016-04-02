package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import com.wecanteven.MenuView.Drawable;

import java.awt.*;

/**
 * Created by John on 4/1/2016.
 */
public abstract class SelectableItem extends Drawable {

    private SelectableMenuItemCommand command;

    public void select(){
        try {
            command.execute();
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

    }

    public SelectableMenuItemCommand getCommand() {
        return command;
    }

    public void setCommand(SelectableMenuItemCommand command) {
        this.command = command;
    }
}
