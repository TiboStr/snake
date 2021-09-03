package game;

import java.io.IOException;



public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("snake");

        Model model = new Model();
        Screen screen = new Screen(model);
        View view = new View(model, screen);

        boolean gameLoop = true;
        while (gameLoop) {
            model.moveSnake();
            Thread.sleep(200);
        }

    }
}
