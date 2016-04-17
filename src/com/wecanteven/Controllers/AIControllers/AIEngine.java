package com.wecanteven.Controllers.AIControllers;

/**
 * Created by John on 4/16/2016.
 */
public class AIEngine implements  Runnable{

    private AITime aiTime;

    @Override
    public void run() {
        TimeKeeper timeKeeper = new TimeKeeper(2);
        while(true) {
            timeKeeper.startTick();
            aiTime.tick();
            timeKeeper.endTick();
        }
    }

    class TimeKeeper {
        private final long tickTimeMilli;
        private long start = 0;
        public TimeKeeper(long tickTimeMilli) {
            this.tickTimeMilli = tickTimeMilli;
        }
        public void startTick() {
            this.start = System.currentTimeMillis();
        }
        public void endTick() {
            long delta = System.currentTimeMillis() - start;
            if (delta < tickTimeMilli) {
                try {
                    Thread.sleep((tickTimeMilli - delta));
                } catch (Exception e) {}
            } else {

            }
        }
    }
}
