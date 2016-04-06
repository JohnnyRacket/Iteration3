package com.wecanteven.AreaView.ViewObjects.Hominid;

import com.wecanteven.AreaView.ViewTime;

/**
 * Created by Alex on 4/6/2016.
 */
public abstract class LimbStrategies {

    public abstract void animate(double percentage);

    public void execute(long duration) {
        long startTime = ViewTime.getInstance().getCurrentTime();

    }

    private void step(long startTime, long duration) {
        double percentage = calculatePercentage(startTime, duration);
        if (percentage < 1) {
            animate(percentage);
            ViewTime.getInstance().register(() -> step(startTime, duration), 1);
        }

    }

    private double calculatePercentage(long startTime, long duration) {
        long t = ViewTime.getInstance().getCurrentTime();
        double percentage = (double)(t-startTime)/duration;
        return percentage < 1 ? percentage : 1;
    }
}
