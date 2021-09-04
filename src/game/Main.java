package game;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("snake");

        Model model = new Model();
        Screen screen = new Screen(model);
        View view = new View(model, screen);

        boolean gameLoop = true;
        model.addApple(new NormalApple());
        while (gameLoop) {
            model.actSnake();
            int random = Constants.RG.nextInt(1000);
            if (random % 25 == 0) {
                model.addApple(new NormalApple());
            }
            if (random % 201 == 0) {
                model.addApple(new SpecialApple());
            }
            Thread.sleep(Constants.STEP_TIME_MILLIS);
        }

    }
}
