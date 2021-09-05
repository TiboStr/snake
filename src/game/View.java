package game;

import javax.swing.*;

public class View implements ViewInterface {

    private final Model model;
    private final Screen screen;

    public View(Model model, Screen screen) {
        this.model = model;
        this.screen = screen;
        model.addListener(this);
        modelHasChanged();
    }

    @Override
    public void modelHasChanged() {

        if (!model.isGameOver()) {
            screen.clear();
            model.getSnake().forEach(s -> screen.draw(s.getColor(), s.getLocation()));
            model.getApples().forEach(a -> screen.draw(a.getColor(), a.getLocation()));
            screen.setScore(model.getScore());
           screen.commitChange();
        }
    }
}
