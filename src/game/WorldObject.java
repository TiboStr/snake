package game;

import java.awt.*;

public abstract class WorldObject {

    private Point location;

    public WorldObject(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    abstract Color getColor();
}
