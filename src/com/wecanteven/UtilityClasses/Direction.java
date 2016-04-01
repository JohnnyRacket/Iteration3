package com.wecanteven.UtilityClasses;

/**
 * Created by Alex on 3/31/2016.
 */
public enum Direction {
    NORTH(Math.PI / 2, (Directionalizable d) -> d.setNorth()),
    SOUTH(Math.PI / 2 * 3, (Directionalizable d) -> d.setSouth()),
    NORTHEAST(Config.TILT_ANGLE, (Directionalizable d) -> d.setNorthEast()),
    NORTHWEST(Math.PI - Config.TILT_ANGLE, (Directionalizable d) -> d.setNorthWest()),
    SOUTHWEST(Math.PI + Config.TILT_ANGLE, (Directionalizable d) -> d.setSouthWest()),
    SOUTHEAST(2 * Math.PI - Config.TILT_ANGLE, (Directionalizable d) -> d.setSouthEast());

    private final double angle;
    private final Command action;

    Direction(double angle, Command action) {
        this.angle = angle;
        this.action = action;
    }

    public double getAngle() {
        return angle;
    }

    public void setDirectionOf(Directionalizable target) {
        action.execute(target);
    }

    public Location getCoords;

    static {
        NORTH.getCoords = new Location(0, -1, 0);
        NORTHEAST.getCoords = new Location(1, -1, 0);
        NORTHWEST.getCoords = new Location(-1, 0, 0);
        SOUTH.getCoords = new Location(0, 1, 0);
        SOUTHEAST.getCoords = new Location(1, 0, 0);
        SOUTHWEST.getCoords = new Location(-1, 1, 0);
    }

    private interface Command {
        void execute(Directionalizable target);
    }

}
