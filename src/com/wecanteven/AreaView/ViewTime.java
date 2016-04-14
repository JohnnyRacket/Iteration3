package com.wecanteven.AreaView;

import com.wecanteven.UtilityClasses.Tuple;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Alex on 3/31/2016.
 */
public class ViewTime {

    private boolean paused = false;

    private CopyOnWriteArrayList<Tuple<vCommand, Long>> staging = new CopyOnWriteArrayList<>();
    private PriorityQueue<Tuple<vCommand, Long>> executables = new PriorityQueue<>(
            (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
    );

    private CopyOnWriteArrayList<Tuple<vCommand, Long>> stagingCopy;
    private PriorityQueue<Tuple<vCommand, Long>> executablesCopy;

    private long currentTime;


    public void tick() {
        this.currentTime = System.currentTimeMillis();

        while (!staging.isEmpty()) {
            executables.add(staging.remove(0));
        }

        while (readyToExecute()) {
            executables.poll().x.execute();
        }

    }

    public boolean readyToExecute() {
        return (!executables.isEmpty() ) &&
                executables.peek().y <= currentTime;
    }

    public void register(vCommand action, long time) {
        if (time <= 0) time = 1;
        staging.add(new Tuple<>(action, time + currentTime ));
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

    public void pause(){
        System.out.println("PAUSING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if(!paused) {
            paused = true;
            stagingCopy = staging;
            staging = new CopyOnWriteArrayList<>();
            executablesCopy = executables;
            executables = new PriorityQueue<>(
                    (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
            );
        }
    }
    public void resume(){
        System.out.println("RESUMING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if(paused) {
            paused = false;
            staging = stagingCopy;
            executables = executablesCopy;
        }
    }

}
