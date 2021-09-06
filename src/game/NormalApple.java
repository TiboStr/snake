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
        return 4;
    }

}
