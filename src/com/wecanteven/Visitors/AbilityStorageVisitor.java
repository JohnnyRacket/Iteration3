package com.wecanteven.Visitors;

import com.wecanteven.Models.Storage.AbilityStorage.AbilityEquipment;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityInventory;
import com.wecanteven.Models.Storage.AbilityStorage.AbilityStorage;

/**
 * Created by simonnea on 4/17/16.
 */
public interface AbilityStorageVisitor {
        void visitAbilityStorage(AbilityStorage itemStorage);
        void visitAbilityEquiped(AbilityEquipment equipment);
        void visitAbilityInventory(AbilityInventory inventory);
}
