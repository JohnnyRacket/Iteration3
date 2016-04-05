package com.wecanteven.Visitors;

import com.wecanteven.Models.Map.Column;
import com.wecanteven.Models.Map.Map;

/**
 * Created by Joshua Kegley on 4/4/2016.
 */
public interface ColumnVisitor {

    public void visitColumn(Column column);
}
