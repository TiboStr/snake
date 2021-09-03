package game;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("snake");

        Model model = new Model();
        Screen screen = new Screen(model);
        View view = new View(model, screen);

    }
}
