package com.wecanteven.Visitors;

import com.wecanteven.Models.Items.Item;

/**
 * Created by simonnea on 3/31/16.
 */
public interface ItemVisitor {
    void visit(Item item);
}
