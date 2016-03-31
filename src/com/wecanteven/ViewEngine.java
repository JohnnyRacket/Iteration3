package com.wecanteven;
import javax.swing.*;
import java.awt.*;

/**
 * Created by alexs on 3/29/2016.
 */
public class ViewEngine extends JFrame implements Runnable{
    private JPanel target;
    private Thread thread;


    public void clear() {
        if (target != null) {
            remove(this.target);
        }
    }

    public void registerView(JPanel jPanel) {
        clear();
        initUI(jPanel);
    }

    private void initUI(JPanel target) {
        this.target = target;
        add(target);
        setTitle("We Cant Even");
        setResizable(true);
        setPreferredSize(new Dimension(500,500));
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        TimeKeeper timeKeeper = new TimeKeeper(30);
        while(true) {
            timeKeeper.startTick();
            this.repaint();
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
