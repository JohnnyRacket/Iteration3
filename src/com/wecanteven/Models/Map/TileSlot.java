package com.wecanteven.Models.Map;

import com.wecanteven.Visitors.MapVisitor;

/**
 * Created by John on 3/31/2016.
 */
public class TileSlot<T> implements MapVisitable{
    private T t;

    public boolean add(T t){
        if(this.isEmpty()){
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

    @Override
    public void accept(MapVisitor visitor) {
        visitor.visitTileSlot(this);
    }

    public boolean isEmpty(){
        return (this.t == null) ? true : false;
    }
}
