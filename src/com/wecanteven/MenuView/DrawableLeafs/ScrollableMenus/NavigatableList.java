package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 3/31/2016.
 */
public class NavigatableList{

    private List<ScrollableMenuItem> list;
    private int currentIndex = 0;

    public NavigatableList(){
        list = new LinkedList<>();
    }


    //Public api
    public void select(){
        list.get(currentIndex).select();
    }

    public void addItem(ScrollableMenuItem item){
        list.add(item);
    }

    public void removeItem(ScrollableMenuItem item){
        list.remove(item);
    }

    public void addItemToIndex(ScrollableMenuItem item, int index){
        list.add(index, item);
    }

    public ScrollableMenuItem removeItemFromIndex(int index){
        return list.remove(index);
    }

    public Iterator<ScrollableMenuItem> getIterator(){
        return list.iterator(); //idk about this
    }
    public int getCurrentIndex(){
        return this.currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        if (currentIndex < 0) return;
        if (currentIndex >= list.size()) return;
        
        this.currentIndex = currentIndex;
    }

    public ScrollableMenuItem getCurrentMenuItem(){
        return list.get(currentIndex);
    }
}
