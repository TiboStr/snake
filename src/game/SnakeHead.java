package game;

import java.awt.*;

public class SnakeHead extends SnakePart {

    public SnakeHead(Model model, Point startingLocation, Direction direction) {
        super(model, startingLocation, direction);
    }

    public void move(Direction direction) {
        if (direction == this.direction || direction.getOpposite() == this.direction) {
            this.location = this.direction.newLocationAfterMoving(this.location);
        } else {
            this.location = direction.newLocationAfterMoving(this.location);

            this.direction = direction;
        }
    }

    public Color getColor() {
        return new Color(34, 148, 64);
    }
}
