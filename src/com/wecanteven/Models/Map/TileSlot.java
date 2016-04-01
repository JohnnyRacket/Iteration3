package com.wecanteven.Models.Map;

/**
 * Created by John on 3/31/2016.
 */
public class TileSlot<T> {
    private T t;

    public boolean add(T t){
        if(this.t == null){
            this.t = t;
            return true;
        }else{
            return false;
        }
    }

    public boolean remove(T t){
        if(this.t == t){
            this.t = null;
            return true;
        }else{
            return false;
        }

    }
}
