package game;

import java.awt.*;

public class SnakePart extends WorldObject {

    protected Direction direction;
    protected Model model;

    public SnakePart(Model model, Point startingLocation, Direction direction) {
        super(startingLocation);
        this.model = model;
        this.location = startingLocation;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }


    public void move() {
        SnakePart neighbour = model.getNeighbouringPart(this);
        this.location = neighbour.location;
        this.direction = neighbour.getDirection();


    }

    @Override
    public Color getColor() {
        return new Color(102, 227, 135);

    }


}
