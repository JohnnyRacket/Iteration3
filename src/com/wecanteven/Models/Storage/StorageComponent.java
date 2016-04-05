package com.wecanteven.Models.Storage;

import com.wecanteven.Models.Storage.ItemStorage.ItemStorage;

/**
 * Created by simonnea on 4/4/16.
 */
public class StorageComponent {
    private ItemStorage owner;

    public StorageComponent(ItemStorage owner) {
        setOwner(owner);
    }

    public void setOwner(ItemStorage owner) {
        this.owner = owner;
    }

    public ItemStorage getOwner() {
        return this.owner;
    }
}
