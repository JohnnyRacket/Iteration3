package com.wecanteven.Visitors;

import com.wecanteven.Models.Skills.SkillManager;

/**
 * Created by simonnea on 4/8/16.
 */
public interface SkillVisitor {
    void visitSkillManager(SkillManager manager);
}
