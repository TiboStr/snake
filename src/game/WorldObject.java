package game;

import java.awt.*;

public abstract class WorldObject {

    protected Point location;

    public WorldObject(Point location) {
        this.location = location;
    }

    abstract Color getColor();
}
