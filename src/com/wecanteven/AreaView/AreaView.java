package com.wecanteven.AreaView;


import com.wecanteven.AreaView.DynamicImages.DynamicImageFactory;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.DynamicImageDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.DrawingStategies.HexDrawingStrategy;
import com.wecanteven.AreaView.ViewObjects.Factories.PlainsViewObjectFactory;
import com.wecanteven.AreaView.ViewObjects.Hominid.HominidViewObject;
import com.wecanteven.AreaView.ViewObjects.LeafVOs.SimpleViewObject;
import com.wecanteven.AreaView.ViewObjects.TileViewObject;
import com.wecanteven.AreaView.ViewObjects.ViewObject;
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
        DynamicImageDrawingStrategy dStrat = new HexDrawingStrategy();

        for (int i = 0; i< 6; i++) {
            for (int j = 0; j<6; j++) {
                backingArray.add(new SimpleViewObject(new Position(i,j,0), DynamicImageFactory.getInstance().loadDynamicImage("Terrain/Grass.xml"), dStrat));
            }
        }

        for (int i = 2; i< 4; i++) {
            for (int j = 1; j<4; j++) {
                backingArray.add(new SimpleViewObject(new Position(i,j,1), DynamicImageFactory.getInstance().loadDynamicImage("Terrain/Grass.xml"), dStrat));
            }
        }
        for (int i = 3; i< 4; i++) {
            for (int j = 1; j<3; j++) {
                backingArray.add(new SimpleViewObject(new Position(i,j,2), DynamicImageFactory.getInstance().loadDynamicImage("Terrain/Grass.xml"), dStrat));
            }
        }
        PlainsViewObjectFactory factory = new PlainsViewObjectFactory();


        Directional entity = new Directional() {
            @Override
            public Direction getDirection() {
                return TEST_DIRECTION;
            }
        };

        HominidViewObject testAvatar = factory.createSneak(new Position(3,2, 2), Direction.NORTH, entity);

        backingArray.add(testAvatar);


        int half = 300;
        int full = half*6;
        for (int i = 0; i < 20; i++) {

            ViewTime.getInstance().register(() -> {
                TEST_DIRECTION = Direction.NORTHEAST;
                testAvatar.update();
            }, half + full*i);

            ViewTime.getInstance().register(() -> {
                TEST_DIRECTION = Direction.SOUTHEAST;
                testAvatar.update();
            }, 2*half + full*i);

            ViewTime.getInstance().register(() -> {
                TEST_DIRECTION = Direction.SOUTH;
                testAvatar.update();
            }, 3*half + full*i);

            ViewTime.getInstance().register(() -> {
                TEST_DIRECTION = Direction.SOUTHWEST;
                testAvatar.update();
            }, 4*half + full*i);

            ViewTime.getInstance().register(() -> {
                TEST_DIRECTION = Direction.NORTHWEST;
                testAvatar.update();
            }, 5*half + full*i);

            ViewTime.getInstance().register(() -> {
                TEST_DIRECTION = Direction.NORTH;
                testAvatar.update();
            }, 6*half + full*i);
        }
    }

    public static Direction TEST_DIRECTION = Direction.NORTH;

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

        public void add(ViewObject vo) {
            int x = convertToX(vo.getPosition());
            int y = convertToY(vo.getPosition());
            int z = convertToZ(vo.getPosition());
            fit(x,y,z);
            cube.get(y).get(z).get(x).add(vo);
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
