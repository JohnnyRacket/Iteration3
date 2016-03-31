package com.wecanteven.MenuView;

import java.awt.*;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by John on 3/31/2016.
 */
public class ViewManager {
    //needs a stack of view things.
    Stack<SwappableView> viewStack = new Stack<>();

    public void draw(Graphics2D g2d, int windowWidth, int windowHeight){
        //System.out.println("in view manager drawing");
        Iterator<SwappableView> iter = viewStack.iterator();
        while(iter.hasNext()){
            iter.next().draw(g2d, windowWidth, windowHeight);
        }
    }

    public void addView(SwappableView view){
        viewStack.push(view);
    }

    public void popView(){
        viewStack.pop();
    }

    public void removeView(SwappableView view){
        viewStack.remove(view);
    }
}
