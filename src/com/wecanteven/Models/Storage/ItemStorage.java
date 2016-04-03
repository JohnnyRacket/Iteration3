package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Items.Takeable.TakeableItem;

import java.util.Iterator;

/**
 * Created by simonnea on 4/1/16.
 */
public abstract class ItemStorage<K extends TakeableItem> {

    public abstract boolean add(K item);

    public abstract boolean remove(K item);

    public abstract boolean hasItem(K item);

    public abstract Iterator<K> getIterator();
}
