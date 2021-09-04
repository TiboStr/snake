package game;

import java.awt.*;

public class SpecialApple extends Apple {

    @Override
    public int getPoints() {
        return 10;
    }

    @Override
    int getGrowth() {
        return 5;
    }

    @Override
    boolean isDead() {
        return Constants.RG.nextInt(100) == 1;
    }

    @Override
    Color getColor() {
        return new Color(255,215,0);
    }
}
