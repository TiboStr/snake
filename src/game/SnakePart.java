package game;

import java.awt.*;

public class SnakePart extends WorldObject {

    private Direction direction;
    private final Model model;

    public SnakePart(Model model, Point startingLocation, Direction direction) {
        super(startingLocation);
        this.model = model;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void move() {
        SnakePart neighbour = model.getNeighbouringPart(this);
        this.setLocation(neighbour.getLocation());
        this.direction = neighbour.getDirection();
    }

    @Override
    public Color getColor() {
        return new Color(102, 227, 135);
    }

}
