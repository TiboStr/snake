package game;

import java.awt.*;

public class SnakeHead extends SnakePart {

    public SnakeHead(Model model, Point startingLocation, Direction direction) {
        super(model, startingLocation, direction);
    }

    public void move(Direction direction) {
        if (direction == this.getDirection() || direction.getOpposite() == this.getDirection()) {
            this.setLocation(this.getDirection().newLocationAfterMoving(this.getLocation()));
        } else {
            this.setLocation(direction.newLocationAfterMoving(this.getLocation()));
            this.setDirection(direction);
        }
    }

    public Color getColor() {
        return new Color(34, 148, 64);
    }
}
