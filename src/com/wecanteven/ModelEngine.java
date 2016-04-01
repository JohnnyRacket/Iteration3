package com.wecanteven;

import com.wecanteven.Models.ModelTime;

/**
 * Created by John on 3/31/2016.
 */
public class ModelEngine implements Runnable{

    private ModelTime modelTime = ModelTime.getInstance();

    @Override
    public void run() {
        TimeKeeper timeKeeper = new TimeKeeper(30);
        while(true) {
            timeKeeper.startTick();
            modelTime.tick();
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
                System.out.println("View tick took too long");
            }
        }
    }
}
