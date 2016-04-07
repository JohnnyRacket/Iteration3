package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewTime;

/**
 * Created by Alex on 4/6/2016.
 */
public abstract class LimbStrategy {

    protected abstract void animate(double percentage);
    protected abstract void complete();

    public final void execute(long duration) {
        long startTime = ViewTime.getInstance().getCurrentTime();
        step(startTime, duration);
    }

    private void step(long startTime, long duration) {
        double percentage = calculatePercentage(startTime, duration);
        if (percentage < 1) {
            animate(percentage);
            ViewTime.getInstance().register(() -> step(startTime, duration), 1);
        } else {
            complete();
        }

    }

    private double calculatePercentage(long startTime, long duration) {
        long t = ViewTime.getInstance().getCurrentTime();
        double percentage = (double)(t-startTime)/duration;
        return percentage < 1 ? percentage : 1;
    }
}
