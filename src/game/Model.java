package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Model implements ModelInterface {

    private List<ViewInterface> listeners;
    private List<SnakePart> snake;

    public Model() {

        listeners = new ArrayList<>();
        this.snake = new ArrayList<>();

        this.snake.add(new SnakeHead(this, new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        this.snake.add(new SnakePart(this, new Point(Constants.SCREEN_WIDTH / 2 - Constants.STEP_SIZE, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        this.snake.add(new SnakePart(this, new Point(Constants.SCREEN_WIDTH / 2 - 2*Constants.STEP_SIZE, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));


    }

    public SnakePart getNeighbouringPart(SnakePart snakePart) {
        int index = snake.indexOf(snakePart);
        if (index == -1) {
            throw new AssertionError("Non existing part");
        }
        if (index == 0) {
            throw new AssertionError("Head element");
        }
        return snake.get(index-1);
    }

    public void moveSnake(Direction dir) {
        IntStream.range(1, snake.size()).map(i -> snake.size() - i).forEach(i -> snake.get(i).move());
        ((SnakeHead) snake.get(0)).move(dir);

        notifyListeners();
    }

    public void moveSnake() {
        moveSnake((snake.get(0)).getDirection());
    }

    public List<SnakePart> getSnake() {
        return snake;
    }

    @Override
    public void addListener(ViewInterface view) {
        listeners.add(view);
    }

    @Override
    public void notifyListeners() {
        listeners.forEach(ViewInterface::modelHasChanged);
    }
}
