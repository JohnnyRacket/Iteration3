package com.wecanteven.AreaView.ViewObjects.DecoratorVOs;

import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.Models.Stats.Stats;
import com.wecanteven.Observers.Observer;
import com.wecanteven.Observers.ViewObservable;

import java.awt.*;

/**
 * Created by Alex on 4/18/2016.
 */
public class StealthVO extends DecoratorViewObject {
    private Stats subject;
    private boolean isStealthed = false;
    public StealthVO(ViewObject child, Stats subject) {
        super(child);
        this.subject = subject;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        super.draw(g);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }


}
