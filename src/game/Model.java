package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class Model implements ModelInterface {

    private final List<ViewInterface> listeners;
    private CopyOnWriteArrayList<SnakePart> snake; // CopyOnWriteArrayList as temporary fix for ConcurrentModificationException
    private CopyOnWriteArrayList<Apple> apples;

    private int score;
    private boolean gameOver;

    public Model() {
        this.listeners = new ArrayList<>();
        this.gameOver = false;
        resetModel();
    }

    private void resetModel() {
        this.snake = new CopyOnWriteArrayList<>(List.of(new SnakeHead(this, new Point(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2), Direction.RIGHT)));
        IntStream.range(0, 6).forEach(i -> addSnakeTail());
        this.apples = new CopyOnWriteArrayList<>(List.of(new NormalApple()));
        this.score = 0;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
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
        List<Apple> toRemove = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getLocation().distance(headLocation) < Constants.STEP_SIZE) {
                toRemove.add(apple);
                IntStream.range(0, apple.getGrowth()).forEach(i -> addSnakeTail());
                score += apple.getPoints();
            }
        }
        apples.removeAll(toRemove);
    }

    public boolean doesSnakeBiteTail() {
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

            if (doesSnakeBiteTail()) {
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
