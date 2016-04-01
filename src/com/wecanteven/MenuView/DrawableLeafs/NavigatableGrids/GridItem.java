package com.wecanteven.MenuView.DrawableLeafs.NavigatableGrids;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableItem;
import com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus.SelectableMenuItemCommand;

import java.awt.*;

/**
 * Created by John on 4/1/2016.
 */
public class GridItem extends Drawable implements SelectableItem {

    private String name;
    private SelectableMenuItemCommand command;

    public GridItem(String name, SelectableMenuItemCommand command){
        this.setCommand(command);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SelectableMenuItemCommand getCommand() {
        return command;
    }

    public void setCommand(SelectableMenuItemCommand command) {
        this.command = command;
    }

    @Override
    public void draw(Graphics2D g2d, int x, int y, int windowWidth, int windowHeight) {

    }

    @Override
    public void select() {
        try {
            command.execute();
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }
}
