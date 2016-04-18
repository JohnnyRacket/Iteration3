package com.wecanteven.UtilityClasses;

/**
 * Created by John on 4/15/2016.
 */
public class DirectionFinder {

    public static Direction getDirection(Location src, Location dst) {
        System.out.print("src: " + src);
        System.out.print("dst: " + dst);
        try {
            int rDiff = src.getR() - dst.getR();
            int sDiff = src.getS() - dst.getS();
            int zDiff = src.getZ() - dst.getZ();


            if (rDiff == 0 && sDiff == 0 && zDiff >= 0) {
                return Direction.DOWN;
            } else if (rDiff == 0 && sDiff == 0 && zDiff <= 0) {
                return Direction.UP;
            } else if (rDiff <= -1 && sDiff == 0) {
                return Direction.SOUTHEAST;
            } else if (rDiff <= -1 && sDiff >= 1) {
                return Direction.NORTHEAST;
            } else if (rDiff == 0 && sDiff >= 1) {
                return Direction.NORTH;
            } else if (rDiff == 0 && sDiff <= -1) {
                return Direction.SOUTH;
            } else if (rDiff >= 1 && sDiff <= -1) {
                return Direction.SOUTHWEST;
            } else if (rDiff >= 1 && sDiff == 0) {
                return Direction.NORTHWEST;
            } else if (rDiff >= 1 && sDiff >= 1) {
                return Direction.NORTHWEST;//these are strange cases, that shouldn;t really happen
            } else if (rDiff <= -1 && sDiff <= -1) {
                return Direction.SOUTHEAST;//these are strange cases, that shouldn't really happen
            } else {
                System.out.println("fail");
                return null;

                //error
            }
            }catch(NullPointerException e){
                return null;
            }
        }

}
