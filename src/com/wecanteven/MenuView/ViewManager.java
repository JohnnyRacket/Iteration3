package com.wecanteven.MenuView;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.DrawableLeafs.Toaster.Toast;
import com.wecanteven.MenuView.DrawableLeafs.Toaster.Toaster;

import java.awt.*;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by John on 3/31/2016.
 */
public class ViewManager {
    //needs a stack of view things.
    Stack<SwappableView> viewStack = new Stack<>();
    Stack<SwappableView> permViewStack = new Stack<>();
    Toaster toaster = new Toaster();

    public void draw(Graphics2D g2d, int windowWidth, int windowHeight) {
        int i = 0;
        Stack<SwappableView> tmpPermStack = (Stack<SwappableView>) permViewStack.clone();
        Iterator<SwappableView> iter = tmpPermStack.iterator();
        while (iter.hasNext()) {
            //
            iter.next().draw(g2d, windowWidth, windowHeight);
        }


        Stack<SwappableView> tmpStack = (Stack<SwappableView>) viewStack.clone();
        iter = tmpStack.iterator();
        while (iter.hasNext()) {
            //
            iter.next().draw(g2d, windowWidth, windowHeight);
        }

        toaster.draw(g2d, windowWidth, windowHeight);


    }

    public void addPermView(SwappableView view){
        permViewStack.push(view);
    }

    public void addView(SwappableView view){
        viewStack.push(view);
    }

    public void popView() {
        if(!viewStack.empty()) {
            SwappableView view = viewStack.peek();
            view.closeDrawables();
            ViewTime.getInstance().register(() -> {
                viewStack.remove(view);
            }, 200);
        }
    }

    public void addToast(Toast toast) { toaster.addToast(toast); }

    public void clear(){
        Iterator<SwappableView> iter =  viewStack.iterator();
        while(iter.hasNext()){
            SwappableView view = iter.next();
            view.closeDrawables();
            ViewTime.getInstance().register(()->{
                viewStack.remove(view);
            },200);
        }
    }

    public void removeView(SwappableView view){
        viewStack.remove(view);
    }
}
