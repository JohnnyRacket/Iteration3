package com.wecanteven.AreaView;


import com.wecanteven.AreaView.Biomes.Biome;
import com.wecanteven.AreaView.ViewObjects.Factories.PlainsViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Factories.ViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Tiles.TileViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
import com.wecanteven.GameLaunching.LevelFactories.LevelFactory;
import com.wecanteven.Models.Entities.Avatar;
import com.wecanteven.Models.Map.Map;
import com.wecanteven.Models.Map.Tile;
import com.wecanteven.UtilityClasses.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by alexs on 3/29/2016.
 */
public class AreaView extends JPanel {



    private xySorted3DArray backingArray = new xySorted3DArray();
    private ViewObjectFactory factory;

    private BackgroundDrawable background;

    public AreaView(Map map, LevelFactory levelFactory) {
        setDoubleBuffered(true);
        this.factory = new ViewObjectFactory(this, map);
        Biome biome = levelFactory.createBiomes(factory);
        VOCreationVisitor voCreationVisitor = new VOCreationVisitor(this, factory.getSimpleVOFactory(), factory, biome);
        map.accept(voCreationVisitor);
        map.setVOCreationVisitor(voCreationVisitor);





        //Entity entity = new Entity();
//        ViewObject testAvatar = factory.createSneak(new Position(avatar.getLocation().getR(),avatar.getLocation().getS(), avatar.getLocation().getZ()), Direction.NORTH, avatar);
//
//        addViewObject(testAvatar);
//
//        InteractiveItem iItem = new InteractiveItem("Button");
//        ViewObject button = factory.createInteractableItem(new Position(3,2,1), iItem);
//        addViewObject(button);
//
//
//        ViewTime.getInstance().register(iItem::trigger, 1500);
//        ViewTime.getInstance().register(iItem::release, 3000);


        int half = 1000;
        int full = half*7;
//        for (int i = 0; i < 20; i++) {
//
//            ViewTime.getInstance().register(() -> {
//                avatar.setDirection(Direction.NORTH);
//                avatar.setLocation(new Location(3,1,1));
//                avatar.setMovingTicks(20);
//            }, half + full*i);
//
//            ViewTime.getInstance().register(() -> {
//                avatar.setDirection(Direction.NORTHEAST);
//                avatar.setLocation(new Location(4,0,1));
//                avatar.setMovingTicks(20);
//
//            }, 2*half + full*i);
//
//            ViewTime.getInstance().register(() -> {
//                avatar.setDirection(Direction.SOUTHEAST);
//                avatar.setLocation(new Location(5,0,2));
//                avatar.setMovingTicks(20);
//            }, 3*half + full*i);
//
//            ViewTime.getInstance().register(() -> {
//                avatar.setDirection(Direction.SOUTH);
//                avatar.setLocation(new Location(5,1,1));
//                avatar.setMovingTicks(20);
//
//            }, 4*half + full*i);
//
////            ViewTime.getInstance().register(() -> {
////                avatar.setLocation(new Location(5,0,1));
////                avatar.setMovingTicks(20);
////            }, 5*half + full*i);
//
//            ViewTime.getInstance().register(() -> {
//                avatar.setDirection(Direction.SOUTHWEST);
//                avatar.setLocation(new Location(4,2,1));
//                avatar.setMovingTicks(20);
//            }, 6*half + full*i);
//
//            ViewTime.getInstance().register(() -> {
//                avatar.setDirection(Direction.NORTHWEST);
//                avatar.setLocation(new Location(3,2,1));
//                avatar.setMovingTicks(20);
//            }, 7*half + full*i);
//        }
    }

    public TileViewObject getTileViewObject(Position p) {
        return backingArray.getTile(p);
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

    public void reveal(Position p) {
        backingArray.reveal(p);
    }
    public void conceal(Position p) {
        backingArray.conceal(p);
    }

    public void setBackground(BackgroundDrawable background) {
        this.background = background;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        background.draw(g2d);
        backingArray.draw(g2d);
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
            try {
                get(x, y, z);
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            get(x, y, z).remove(vo);
        }

        public TileViewObject getTile(Position p) {
            int x = convertToX(p);
            int y = convertToY(p);
            int z = convertToZ(p);
            return get(x, y, z);
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

        public void reveal(Position p) {
            int x = convertToX(p);
            int y = convertToY(p);
            for (int i = 0; i < zSize; i++) {
                if (!isSafe(x, y, i)) return;
                get(x, y, i).reveal();
            }
        }

        public void conceal(Position p) {
            int x = convertToX(p);
            int y = convertToY(p);
            for (int i = 0; i < zSize; i++) {
                if (!isSafe(x, y, i)) return;
                get(x, y, i).conceal();
            }
        }

        private boolean isSafe(int x, int y, int z) {
            return  x >=0 &&
                    y >=0 &&
                    z >=0 &&
                    x < xSize &&
                    y < ySize &&
                    z < zSize;
        }

        private TileViewObject get(int x, int y, int z) {
            //
            return cube.get(y).get(z).get(x);
        }

        private void fit(int xReal, int yReal, int zReal) {
            int x = xReal + 1;
            int y = yReal + 1;
            int z = zReal + 1;
            //add xzPlanes until the Rs line up
            for (; ySize <= y; ySize++) {
                cube.add(xzPlane(ySize));
            }

            //add xRows to every yzPlane till the Zs line up
            for(; zSize <= z; zSize++) {
                for (int j = 0; j < ySize; j++) {
                    cube.get(j).add(xRow(j, zSize));
                }
            }

            //append tVOs to every xColumn until the x's line up
            for(; xSize <= x; xSize++) {
                for (int j = 0; j < ySize; j++) {
                    ArrayList<ArrayList<TileViewObject>> xzPlane = cube.get(j);
                    for (int k = 0; k< zSize; k++) {
                        xzPlane.get(k).add(new TileViewObject(convertToLocation(xSize, j, k)));
                    }
                }
            }


            //
        }

        private ArrayList<TileViewObject> xRow(int y, int z) {
            ArrayList<TileViewObject> xRow = new ArrayList<>();
            for (int i = 0; i< xSize; i++) {
                xRow.add(new TileViewObject(convertToLocation(i, y, z)));
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

        private Location convertToLocation(int x, int y, int z) {
            return new Location(
                    x,
                    (y-x)/2,
                    z
            );
        }

        private int convertToY(Position p) {
            return p.getLocation().getR() + p.getLocation().getS()*2;
        }
        private int convertToZ(Position p) {
            return p.getLocation().getZ();
        }

    }
}
