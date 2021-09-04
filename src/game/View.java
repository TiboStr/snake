package game;

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
            if (model.getSnake().size() <= 1) {
                System.out.print("fout");
            }
            model.getSnake().forEach(s -> screen.draw(s.getColor(), s.getLocation()));
            model.getApples().forEach(a -> screen.draw(a.getColor(), a.getLocation()));
            screen.commitChange();
        }
    }
}
