package com.wecanteven.Controllers.AIControllers.ActionControllers;

import com.wecanteven.Controllers.AIControllers.Targets.EnemyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.FriendlyTarget;
import com.wecanteven.Controllers.AIControllers.Targets.ItemTarget;
import com.wecanteven.Controllers.AIControllers.Targets.NullTarget;
import com.wecanteven.Models.Entities.Character;
import com.wecanteven.Models.Map.Map;

/**
 * Created by John on 4/16/2016.
 */
public class PetActionController extends AbstractActionController {

    public PetActionController(Character character, Map map) {
        super(character, map);
    }

    @Override
    public void visitItemTarget(ItemTarget target) {

    }

    @Override
    public void visitEnemyTarget(EnemyTarget target) {

    }

    @Override
    public void visitFriendlyTarget(FriendlyTarget target) {

    }

    @Override
    public void visitNullTarget(NullTarget target) {

    }
}
