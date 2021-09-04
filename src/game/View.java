package game;

public class View implements ViewInterface{

    private Model model;
    private Screen screen;

    public View(Model model, Screen screen) {
        this.model = model;
        this.screen = screen;
        model.addListener(this);
        modelHasChanged();
    }

    @Override
    public void modelHasChanged() {
        screen.clear();
        model.getSnake().forEach(l -> screen.draw(l.getColor(), l.location));
        model.getApples().forEach(e -> screen.draw(e.getColor(), e.location));
        screen.commitChange();

    }
}
