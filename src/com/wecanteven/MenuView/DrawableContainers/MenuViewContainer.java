package com.wecanteven.MenuView.DrawableContainers;

import com.wecanteven.MenuView.Drawable;
import com.wecanteven.MenuView.Navigatable;

import java.util.ArrayList;

/**
 * Created by John on 3/31/2016.
 */
public class MenuViewContainer implements Navigatable {

    private ArrayList<Navigatable> menus = new ArrayList<>();
    private int current;

    @Override
    public void up() {
        System.out.println("doing up");
        menus.get(current).up();
    }

    @Override
    public void down() {
        menus.get(current).down();
    }

    @Override
    public void left() {
        menus.get(current).left();
    }

    @Override
    public void right() {
        menus.get(current).right();
    }

    @Override
    public void select() {
        menus.get(current).select();
    }

    public void swap() {
        System.out.println("swapping, size is: " + menus.size());
        if (current < menus.size() - 1) {
            ++current;
        } else {
            current = 0;
        }
        System.out.println("current is:" + current);
    }

    public void add(Navigatable navigatable){
        menus.add(navigatable);
    }
    public void remove(Navigatable navigatable){
        menus.remove(navigatable);
    }
}
