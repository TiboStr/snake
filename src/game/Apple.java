package game;

import java.awt.*;

public abstract class Apple extends WorldObject{


    public Apple() {
        super(new Point(Constants.RG.nextInt(Constants.SCREEN_WIDTH), Constants.RG.nextInt(Constants.SCREEN_HEIGHT)));
    }

    abstract int getPoints();

    abstract int getGrowth();

    abstract boolean isDead();
}
