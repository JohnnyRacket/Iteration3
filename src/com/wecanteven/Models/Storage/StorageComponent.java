package com.wecanteven.Models.Storage;

/**
 * Created by simonnea on 4/4/16.
 */
public class StorageComponent {
    private Storage owner;

    public StorageComponent(Storage owner) {
        setOwner(owner);
    }

    public void setOwner(Storage owner) {
        this.owner = owner;
    }

    public Storage getOwner() {
        return this.owner;
    }
}
