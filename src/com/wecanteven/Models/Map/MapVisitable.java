package com.wecanteven.Models.Map;

import com.wecanteven.Visitors.MapVisitor;

/**
 * Created by John on 3/31/2016.
 */
public interface MapVisitable {
    void accept(MapVisitor visitor);
}
