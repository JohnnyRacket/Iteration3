package com.wecanteven.AreaView.ViewObjects.Hominid.LimbStrategies;

import com.wecanteven.AreaView.ViewTime;

/**
 * Created by Alex on 4/6/2016.
 */
public abstract class LimbStrategy {

    protected abstract void animate(double percentage);
    protected abstract void complete();

    private boolean isStopped = false;
    private boolean isExecuting = false;

    private long duration;

    public final void execute(long duration) {
        long startTime = ViewTime.getInstance().getCurrentTime();
        isExecuting = true;
        this.duration = duration;
        step(startTime, duration);
    }

    public final void stop() {
        if (isExecuting) {
            isStopped = true;
        }
    }

    public final boolean isExecuting() {
        return isExecuting;
    }

    private void step(long startTime, long duration) {
        double percentage = calculatePercentage(startTime, duration);
        if (percentage < 1 && !isStopped) {
            animate(percentage);
            ViewTime.getInstance().register(() -> step(startTime, duration), 1);
        } else {
            isStopped = false;
            isExecuting = false;
            complete();
        }

    }

    protected long getDuration() {
        return duration;
    }

    private double calculatePercentage(long startTime, long duration) {
        long t = ViewTime.getInstance().getCurrentTime();
        double percentage = (double)(t-startTime)/duration;
        return percentage < 1 ? percentage : 1;
    }
}
