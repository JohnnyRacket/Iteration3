package com.wecanteven.AreaView;

import com.wecanteven.UtilityClasses.Tuple;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Alex on 3/31/2016.
 */
public class ViewTime {
    private PriorityQueue<Tuple<vCommand, Long>> executables = new PriorityQueue<>(
            (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
    );

    private long currentTime;


    public void tick() {
        System.out.println("tick start");
        this.currentTime = System.currentTimeMillis();

        while (readyToExecute()) {
            executables.poll().x.execute();
        }

        System.out.println("tick end");
    }

    public boolean readyToExecute() {
        return (!executables.isEmpty() ) &&
                executables.peek().y <= currentTime;
    }

    public void register(vCommand action, long time) {
        if (time <= 0) time = 1;
        executables.add(new Tuple<>(action, time + currentTime ));
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public interface vCommand {
        void execute();
    }

    private static ViewTime ourInstance = new ViewTime();

    public static ViewTime getInstance() {
        return ourInstance;
    }

    private ViewTime() {
        currentTime = System.currentTimeMillis();
    }

}
