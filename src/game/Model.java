package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Model implements ModelInterface {

    private final List<ViewInterface> listeners;
    private final List<SnakePart> snake;
    private final List<Apple> apples;

    private int score;
    private boolean gameOver;

    public Model() {

        this.listeners = new ArrayList<>();
        this.snake = new ArrayList<>();
        this.apples = new ArrayList<>();
        this.score = 0;
        this.gameOver = false;

        this.snake.add(new SnakeHead(this, new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        //this.snake.add(new SnakePart(this, new Point(Constants.SCREEN_WIDTH / 2 - Constants.STEP_SIZE, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        //this.snake.add(new SnakePart(this, new Point(Constants.SCREEN_WIDTH / 2 - 2 * Constants.STEP_SIZE, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));

    }

    public void resetModel() {
        this.snake.clear();
        this.apples.clear();
        this.score = 0;
        this.snake.add(new SnakeHead(this, new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        addSnakeTail();
        //this.snake.add(new SnakePart(this, new Point(Constants.SCREEN_WIDTH / 2 - Constants.STEP_SIZE, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        //this.snake.add(new SnakePart(this, new Point(Constants.SCREEN_WIDTH / 2 - 2 * Constants.STEP_SIZE, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT));
        //notifyListeners();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean state) {
        gameOver = state;
        notifyListeners();
    }

    public SnakePart getNeighbouringPart(SnakePart snakePart) {
        int index = snake.indexOf(snakePart);
        if (index == -1) {
            throw new AssertionError("Non existing part");
        }
        if (index == 0) {
            throw new AssertionError("Head element");
        }
        return snake.get(index - 1);
    }

    public void addApple(Apple apple) {
        apples.add(apple);
        notifyListeners();
    }

    public List<Apple> getApples() {
        return apples;
    }

    public void addSnakeTail() {
        SnakePart last = this.snake.get(this.snake.size() - 1);
        this.snake.add(new SnakePart(this, last.getDirection().getOpposite().newLocationAfterMoving(last.getLocation()), last.getDirection()));
    }

    public void actApple() {
        Point headLocation = snake.get(0).getLocation();
        Iterator<Apple> it = apples.iterator();
        while (it.hasNext()) {
            Apple current = it.next();
            if (current.getLocation().distance(headLocation) < Constants.STEP_SIZE) {
                score += current.getPoints();
                it.remove();
                IntStream.range(0, current.getGrowth()).forEach(i -> addSnakeTail());
            } else if (current.isDead()) {
                it.remove();
            }
        }
    }

    public boolean snakeBitesTail() {
        Point headPosition = this.snake.get(0).getLocation();
        for (int i = 1; i < this.snake.size(); i++) {
            if (headPosition.distance(this.snake.get(i).getLocation()) < Constants.STEP_SIZE) {
                return true;
            }
        }
        return false;
    }

    public void actSnake(Direction dir) {
        if (!gameOver) {
            IntStream.range(1, snake.size()).map(i -> snake.size() - i).forEach(i -> snake.get(i).move());
            ((SnakeHead) snake.get(0)).move(dir);
            actApple();

            if (snakeBitesTail()) {
                gameOver = true;
                resetModel();
            }
        }
        notifyListeners();
    }

    public void actSnake() {
        actSnake((snake.get(0)).getDirection());
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
