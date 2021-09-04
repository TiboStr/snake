package game;

import java.awt.*;

public class NormalApple extends Apple {

    @Override
    Color getColor() {
        return Color.RED;
    }


    @Override
    public int getPoints() {
        return 1;
    }

    @Override
    int getGrowth() {
        return 1;
    }

    @Override
    boolean isDead() {
        return Constants.RG.nextInt(1000) == 1;
    }
}
