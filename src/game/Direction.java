package game;

import java.awt.*;

public enum Direction {

    DOWN {
        @Override
        public Direction getOpposite() {
            return Direction.UP;
        }

        @Override
        public Point newLocationAfterMoving(Point currentLocation) {
            return new Point(currentLocation.x, currentLocation.y + Constants.STEP_SIZE < Constants.SCREEN_HEIGHT ? currentLocation.y + Constants.STEP_SIZE : 0);
        }
    }, LEFT {
        @Override
        public Direction getOpposite() {
            return Direction.RIGHT;
        }

        @Override
        public Point newLocationAfterMoving(Point currentLocation) {
            return new Point(currentLocation.x - Constants.STEP_SIZE >= 0 ? currentLocation.x - Constants.STEP_SIZE : Constants.SCREEN_WIDTH, currentLocation.y);
        }
    }, RIGHT {
        @Override
        public Direction getOpposite() {
            return Direction.LEFT;
        }

        @Override
        public Point newLocationAfterMoving(Point currentLocation) {
            return new Point(currentLocation.x + Constants.STEP_SIZE < Constants.SCREEN_WIDTH ? currentLocation.x + Constants.STEP_SIZE : 0, currentLocation.y);
        }
    }, UP {
        @Override
        public Direction getOpposite() {
            return Direction.DOWN;
        }

        @Override
        public Point newLocationAfterMoving(Point currentLocation) {
            return new Point(currentLocation.x, currentLocation.y - Constants.STEP_SIZE >= 0 ? currentLocation.y - Constants.STEP_SIZE : Constants.SCREEN_HEIGHT);
        }
    };

    public abstract Direction getOpposite();

    public abstract Point newLocationAfterMoving(Point currentLocation);

}
