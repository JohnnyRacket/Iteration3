package com.wecanteven.Visitors;

import com.wecanteven.Models.Entities.Avatar;

/**
 * Created by Joshua Kegley on 4/5/2016.
 */
public interface AvatarVisitor {
    public void visitAvatar(Avatar avatar);
}
