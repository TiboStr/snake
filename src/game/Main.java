package game;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {



        Model model = new Model();
        Screen screen = new Screen(model);
        View view = new View(model, screen);

        model.addApple(new NormalApple());
        while (true) {

            while (!model.isGameOver()) {
                model.actSnake();

                int random = Constants.RG.nextInt(1000);
                if (random % 15 == 0) {
                    model.addApple(new NormalApple());
                }
                if (random % 201 == 0) {
                    model.addApple(new SpecialApple());
                }
                Thread.sleep(Constants.STEP_TIME_MILLIS);
                System.out.println(model.isGameOver());
            }

            screen.gameOverScreen();
        }
    }
}
