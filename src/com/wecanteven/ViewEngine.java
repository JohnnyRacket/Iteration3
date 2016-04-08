package com.wecanteven;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.SwingToDrawableAdapter;
import com.wecanteven.MenuView.ViewManager;
import com.wecanteven.UtilityClasses.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by alexs on 3/29/2016.
 */
public class ViewEngine extends JFrame implements Runnable{
    private JPanel target;
    private Thread thread;
    private ViewTime viewTime = ViewTime.getInstance();
    private ViewManager manager = new ViewManager();
    private SwingToDrawableAdapter adapter = new SwingToDrawableAdapter();

    public ViewEngine(){
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                Config.SCREEN_HEIGHT = getHeight();
                Config.SCREEN_WIDTH = getWidth();
            }
        });
        adapter.setAdaptee(manager);
        this.initUI(adapter);
    }


    public void clear() {
        if (target != null) {
            remove(this.target);
        }
    }

    public void registerView(JPanel jPanel) {
        clear();
        this.target = jPanel;
        initUI(jPanel);
    }

    private void initUI(JPanel target) {
        //this.target = target;
        add(target);
        setTitle("We Cant Even");
        setResizable(true);
        //Maximized the window
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(DimMax);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //dumb
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

            viewTime.tick();
            this.repaint();

            //manager.draw((Graphics2D) this.getGraphics(),Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
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

    public ViewManager getManager() {
        return manager;
    }

    public void setManager(ViewManager manager) {
        this.manager = manager;
    }
}
