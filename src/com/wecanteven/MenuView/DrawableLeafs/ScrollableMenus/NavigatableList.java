package com.wecanteven.MenuView.DrawableLeafs.ScrollableMenus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by John on 3/31/2016.
 */
public class NavigatableList{

    private List<SelectableItem> list;
    private int currentIndex = 0;

    public NavigatableList(){
        list = new LinkedList<>();
    }


    //Public api
    public void select(){
        list.get(currentIndex).select();
    }

    public void addItem(SelectableItem item){
        list.add(item);
    }

    public void removeItem(SelectableItem item){
        list.remove(item);
    }

    public void addItemToIndex(SelectableItem item, int index){
        list.add(index, item);
    }

    public SelectableItem removeItemFromIndex(int index){
        return list.remove(index);
    }

    public Iterator<SelectableItem> getIterator(){
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

    public void clear(){
        this.list.clear();
    }

    public NavigatableList clone(){
        NavigatableList listToReturn = new NavigatableList();
        Iterator<SelectableItem> iter = list.iterator();
        while(iter.hasNext()){
            listToReturn.addItem(iter.next());
        }
        return listToReturn;
    }

    public SelectableItem getCurrentMenuItem(){
        return list.get(currentIndex);
    }
}
