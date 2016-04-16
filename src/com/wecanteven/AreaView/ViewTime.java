package com.wecanteven.AreaView;

import com.wecanteven.MenuView.DrawableLeafs.Toaster.Toast;
import com.wecanteven.UtilityClasses.Tuple;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Alex on 3/31/2016.
 */
public class ViewTime {

    private boolean paused = false;
    private long pausedTime;

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
        System.out.println("PAUSING VIEW~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if(!paused) {
            paused = true;
            pausedTime = System.currentTimeMillis();
            stagingCopy = (CopyOnWriteArrayList<Tuple<vCommand, Long>>)staging.clone();
            staging = new CopyOnWriteArrayList<>();
            executablesCopy =  new PriorityQueue<>(
                    (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
            );
            Iterator<Tuple<vCommand,Long>> iter = executables.iterator();
            while(iter.hasNext()){
                Tuple<vCommand,Long> tuple = iter.next();
                executablesCopy.add(new Tuple(tuple.x, tuple.y));
            }
            executables = new PriorityQueue<>(
                    (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
            );
        }
    }
    public void resume(){
        System.out.println("RESUMING VIEW~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        if(paused) {

            paused = false;
            staging = new CopyOnWriteArrayList<>();
            executables = new PriorityQueue<>(
                    (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
            );
            long timeToAdd = System.currentTimeMillis() - pausedTime;
            //System.out.println(timeToAdd);
            Iterator<Tuple<vCommand,Long>> iter = stagingCopy.iterator();
            while(iter.hasNext()){
                Tuple<vCommand,Long> tuple = iter.next();
                staging.add(new Tuple(tuple.x, tuple.y + timeToAdd));
            }
            iter = executablesCopy.iterator();
            while(iter.hasNext()){
                Tuple<vCommand,Long> tuple = iter.next();
                executables.add(new Tuple(tuple.x, tuple.y + timeToAdd));
            }
            System.out.println( "time to add: " + timeToAdd + "compared to " + (System.currentTimeMillis() - pausedTime));

        }
    }

    public void reset(){
        staging = new CopyOnWriteArrayList<>();
        executables = new PriorityQueue<>(
                (Tuple<vCommand, Long> o1, Tuple<vCommand, Long> o2) ->  (int)(o1.y - o2.y)
        );
    }

}
