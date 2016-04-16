package com.wecanteven;
import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.SwingToDrawableAdapter;
import com.wecanteven.MenuView.ViewManager;
import com.wecanteven.UtilityClasses.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

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
        //This is cool and all, but I need the console for debugging
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        DimMax.setSize(DimMax.width/2, DimMax.height/2);
        setPreferredSize(DimMax);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //dumb
    }


    public void start() {
        thread = new Thread(this);
        thread.setName("View Thread");
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

            }
        }
    }

    public ViewManager getManager() {
        return manager;
    }

    public void setManager(ViewManager manager) {
        this.manager = manager;
    }

    public BufferedImage getScreenShot() {
        Component component = target;
        BufferedImage image = new BufferedImage(
                component.getWidth(),
                component.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY
        );
        // call the Component's paint method, using
        // the Graphics object of the image.
        component.paint( image.getGraphics() ); // alternately use .printAll(..)
        return image;
    }
}
