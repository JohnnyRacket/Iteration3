package com.wecanteven.Models.Storage.EquipmentSlots;

/**
 * Created by simonnea on 4/2/16.
 */

import com.wecanteven.Models.Items.Takeable.Equipable.EquipableItem;
import com.wecanteven.Observers.Observable;

public interface EquipmentSlot extends Observable {
    String getItemName();
}
