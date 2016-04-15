package com.wecanteven.AreaView.ViewObjects.Tiles;


import com.wecanteven.AreaView.ViewObjects.Parallel.DarkenedViewObject;
import com.wecanteven.AreaView.ViewObjects.Parallel.GrayscaleViewObject;
import com.wecanteven.AreaView.ViewObjects.Parallel.ParallelViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.UtilityClasses.Location;
import com.wecanteven.AreaView.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/29/2016.
 */
public class TileViewObject implements ViewObject {
    private Location location;
    private ArrayList<ViewObject> children = new ArrayList<>();
    private TileState state = new ReadyState();

    public TileViewObject(Location location) {
        this.location = location;
    }

    public void add(ViewObject vo) {
        this.children.add(vo);
    }

    private int loomingDeathCounter = 0;

    public void remove(ViewObject vo) {
        if (!decoraterRemove(vo)) {
            System.out.println("LOOMING DEATH IS APPROACHING THE VIEW! Danger level: " + loomingDeathCounter++);
            //ViewTime.getInstance().register(() -> this.remove(vo), 10);
            System.out.println("BAD SHIT HAPPENED IN: TileViewObject.remove(ViewObject)");
        }


//        if (!this.children.remove(vo)) {
//            System.out.println("LOOMING DEATH IS APPROACHING THE VIEW! Danger level: " + loomingDeathCounter++);
//            //ViewTime.getInstance().register(() -> this.remove(vo), 10);
//            System.out.println("BAD SHIT HAPPENED IN: TileViewObject.remove(ViewObject)");
//        } else {
//            if (loomingDeathCounter > 0) {
//                System.out.print("WOOT WOOT!!!!!! Looming death averted");
//                loomingDeathCounter = 0;
//            }
//        }
    }

    public boolean decoraterRemove(ViewObject toRemove) {
        for (int i = children.size() - 1; i>=0; i--) {
            if (toRemove.equals(children.get(i)) ) {
                children.remove(children.get(i));
                return true;
            }
        }
        return false;
    }

    public void reveal() {
        state.reveal();
    }
    public void conceal() {
        state.conceal();
    }

    @Override
    public Position getPosition() {
        return location.toPosition();
    }

    @Override
    public void setPosition(Position p) {
        this.location = p.getLocation();
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public void draw(Graphics2D g) {
        state.draw(g);
    }

    @Override
    public void addToFogOfWarViewObject(ParallelViewObject parallelViewObject) {
        children.forEach( (child) -> child.addToFogOfWarViewObject(parallelViewObject));
    }

    private interface TileState {
        void draw(Graphics2D g);
        void reveal();
        void conceal();
    }
    private class NonvisibleState implements TileState {
        private ParallelViewObject parallelViewObject = new DarkenedViewObject(getPosition());
        public NonvisibleState() {
            children.forEach( (child) -> child.addToFogOfWarViewObject(parallelViewObject));
        }
        @Override
        public void draw(Graphics2D g) {
            parallelViewObject.draw(g);
        }

        @Override
        public void reveal() {
            state = new VisibleState();
        }

        @Override
        public void conceal() {

        }
    }

    private class VisibleState implements TileState {
        @Override
        public void draw(Graphics2D g) {
            for (ViewObject vo: children) {
                vo.draw(g);
            }
        }
        @Override
        public void reveal() {
            //Do nothing
        }

        @Override
        public void conceal() {
            state = new NonvisibleState();
        }
    }

    private class UnknownState implements TileState {
        private ParallelViewObject parallelViewObject = new GrayscaleViewObject(getPosition());
        public UnknownState() {
            children.forEach( (child) -> child.addToFogOfWarViewObject(parallelViewObject));
        }

        @Override
        public void draw(Graphics2D g) {
            parallelViewObject.draw(g);
        }
        @Override
        public void reveal() {
            state = new VisibleState();
        }

        @Override
        public void conceal() {

        }
    }

    public class ReadyState implements TileState {
        @Override
        public void draw(Graphics2D g) {
            state = new UnknownState();
            state.draw(g);
        }

        @Override
        public void reveal() {
            state = new UnknownState();
            state.reveal();
        }

        @Override
        public void conceal() {
            state = new UnknownState();
            state.conceal();
        }
    }
}
