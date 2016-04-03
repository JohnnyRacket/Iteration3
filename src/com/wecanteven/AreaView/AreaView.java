package com.wecanteven.AreaView;


import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.ViewObjects.DecoratorVOs.MovingViewObject;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Factories.PlainsViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.TileViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.GameLaunching.LevelFactories.DopeAssLevelFactory;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.Models.Entities.Entity;
import com.wecanteven.Models.Items.InteractiveItem;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Observers.Directional;
import com.wecanteven.UtilityClasses.Direction;
import com.wecanteven.UtilityClasses.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/29/2016.
 */
public class AreaView extends JPanel {


    //FOR TESTING ONLY
    private xySorted3DArray backingArray = new xySorted3DArray();

    public AreaView() {


        PlainsViewObjectFactory factory = new PlainsViewObjectFactory(this);
        LevelFactory levelFactory = new DopeAssLevelFactory();
        Map map = levelFactory.createMap();
        VOCreationVisitor voCreationVisitor = new VOCreationVisitor(this, factory);
        map.accept(voCreationVisitor);





        Entity entity = new Entity();
        ViewObject testAvatar = factory.createSneak(new Position(3,2, 1), Direction.NORTH, entity);
        addViewObject(testAvatar);

        InteractiveItem iItem = new InteractiveItem("Button");
        ViewObject button = factory.createInteractableItem(new Position(3,2,1), iItem);
        addViewObject(button);


        ViewTime.getInstance().register(iItem::trigger, 1500);
        ViewTime.getInstance().register(iItem::release, 3000);


        int half = 1000;
        int full = half*7;
        for (int i = 0; i < 20; i++) {

            ViewTime.getInstance().register(() -> {
                entity.setDirection(Direction.NORTH);
                entity.setLocation(new Location(3,1,1));
                entity.setMovingTicks(20);
            }, half + full*i);

            ViewTime.getInstance().register(() -> {
                entity.setDirection(Direction.NORTHEAST);
                entity.setLocation(new Location(4,0,1));
                entity.setMovingTicks(20);
                System.out.println("TEST");
            }, 2*half + full*i);

            ViewTime.getInstance().register(() -> {
                entity.setDirection(Direction.SOUTHEAST);
                entity.setLocation(new Location(5,0,2));
                entity.setMovingTicks(20);
            }, 3*half + full*i);

            ViewTime.getInstance().register(() -> {
                entity.setDirection(Direction.SOUTH);
                entity.setLocation(new Location(5,1,1));
                entity.setMovingTicks(20);
                System.out.println("TEST2");
            }, 4*half + full*i);

//            ViewTime.getInstance().register(() -> {
//                entity.setLocation(new Location(5,0,1));
//                entity.setMovingTicks(20);
//            }, 5*half + full*i);

            ViewTime.getInstance().register(() -> {
                entity.setDirection(Direction.SOUTHWEST);
                entity.setLocation(new Location(4,2,1));
                entity.setMovingTicks(20);
            }, 6*half + full*i);

            ViewTime.getInstance().register(() -> {
                entity.setDirection(Direction.NORTHWEST);
                entity.setLocation(new Location(3,2,1));
                entity.setMovingTicks(20);
            }, 7*half + full*i);
        }
    }

    public void addViewObject(ViewObject vo) {
        backingArray.add(vo, vo.getPosition());
    }
    public void addViewObject(ViewObject vo, Position p) {
        backingArray.add(vo, p);
    }
    public void removeViewObject(ViewObject vo, Position p) {
        backingArray.remove(vo, p);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        backingArray.draw(g2d);
        g2d.drawString("WOOT WOOT", 50, 50);
    }

    class xySorted3DArray {
        public ArrayList<ArrayList<ArrayList<TileViewObject>>> cube = new ArrayList<>();
        //stored in y -> z -> x order
        private int xSize;
        private int ySize;
        private int zSize;


        public xySorted3DArray() {
            xSize = 1;
            ySize = 1;
            zSize = 1;
            cube.add(xzPlane(0));

        }

        public void add(ViewObject vo, Position p) {
            int x = convertToX(p);
            int y = convertToY(p);
            int z = convertToZ(p);
            fit(x, y, z);
            get(x, y, z).add(vo);
        }

        public void remove(ViewObject vo, Position p) {
            int x = convertToX(p);
            int y = convertToY(p);
            int z = convertToZ(p);
            get(x, y, z).remove(vo);
        }

        public void draw(Graphics2D g) {
            for (int j = 0; j < ySize; j++) {
                for (int k = 0; k < zSize; k++) {
                    for (int i = 0; i < xSize; i++) {
                        cube.get(j).get(k).get(i).draw(g);
                    }
                }
            }
        }
        private TileViewObject get(int x, int y, int z) {
            return cube.get(y).get(z).get(x);
        }

        private void fit(int x, int y, int z) {

            //add xzPlanes until the Rs line up
            for (; ySize <= y; ySize++) {
                System.out.println("adding y: " + ySize);
                cube.add(xzPlane(ySize));
            }

            //add xRows to every yzPlane till the Zs line up
            for(; zSize <= z; zSize++) {
                System.out.println("adding z: " + zSize);
                for (int j = 0; j < ySize; j++) {
                    cube.get(j).add(xRow(j, zSize));
                }
            }

            //append tVOs to every xColumn until the x's line up
            for(; xSize <= x; xSize++) {
                System.out.println("adding x: " + xSize);
                for (int j = 0; j < ySize; j++) {
                    ArrayList<ArrayList<TileViewObject>> xzPlane = cube.get(j);
                    for (int k = 0; k< zSize; k++) {
                        xzPlane.get(k).add(new TileViewObject(new Location(xSize, j, k)));
                    }
                }
            }


            //
        }

        private ArrayList<TileViewObject> xRow(int y, int z) {
            ArrayList<TileViewObject> xRow = new ArrayList<>();
            for (int i = 0; i< xSize; i++) {
                xRow.add(new TileViewObject(new Location(i, y, z)));
            }
            return xRow;
        }

        private ArrayList<ArrayList<TileViewObject>> xzPlane(int y) {
            ArrayList<ArrayList<TileViewObject>> zxPlane = new ArrayList<>();
            for (int k = 0; k < zSize; k++) {
                zxPlane.add(xRow(y, k));
            }
            return zxPlane;
        }

        private int convertToX(Position p) {
            return p.getLocation().getR();
        }
        private int convertToY(Position p) {
            return p.getLocation().getR() + p.getLocation().getS()*2;
        }
        private int convertToZ(Position p) {
            return p.getLocation().getZ();
        }

    }
}
