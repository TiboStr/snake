package game;

import java.awt.*;

public class SnakePart {

    protected Point location;
    protected Direction direction;
    protected Model model;

    public SnakePart(Model model, Point startingLocation, Direction direction) {
        this.model = model;
        this.location = startingLocation;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Point getLocation() {
        return location;
    }

    public void move() {
        SnakePart neighbour = model.getNeighbouringPart(this);
        this.location = neighbour.getLocation();
        this.direction = neighbour.getDirection();


    }

    public Color getGraphics() {
        return new Color(102, 227, 135);

    }


}
