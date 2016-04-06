package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;

/**
 * Created by simonnea on 4/4/16.
 */
public class StorageComponent<K> {
    private K owner;

    public StorageComponent(K owner) {
        setOwner(owner);
    }

    public void setOwner(K owner) {
        this.owner = owner;
    }

    public K getOwner() {
        return this.owner;
    }
}
