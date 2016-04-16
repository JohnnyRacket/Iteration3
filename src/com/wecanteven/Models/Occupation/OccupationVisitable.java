package com.wecanteven.Models.Occupation;

import com.wecanteven.Visitors.OccupationVisitor;

/**
 * Created by John on 4/15/2016.
 */
public interface OccupationVisitable {
    void accept(OccupationVisitor visitor);
}
