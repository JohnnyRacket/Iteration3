package com.wecanteven.Visitors;

import com.wecanteven.Models.Storage.Equipment;

/**
 * Created by simonnea on 4/1/16.
 */
public interface EquipmentVisitor {
    void visitEquipment(Equipment equipment);
}
