package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JFrame implements KeyListener {

    private JPanel panel;
    private Model model;

    public Screen(Model model) {
        super("Snake");
        this.model = model;
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.white);
        panel = new JPanel();
        panel.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.getContentPane().add(panel);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void clear() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void draw(Color c, Point p) {
        JPanel rectangle = new JPanel();
        rectangle.setSize(Constants.STEP_SIZE, Constants.STEP_SIZE);
        rectangle.setMinimumSize(new Dimension(Constants.STEP_SIZE, Constants.STEP_SIZE));
        rectangle.setMaximumSize(new Dimension(Constants.STEP_SIZE, Constants.STEP_SIZE));
        rectangle.setPreferredSize(new Dimension(Constants.STEP_SIZE, Constants.STEP_SIZE));
        //setLocationRelativeTo(this);

        panel.setLayout(null);

        rectangle.setBackground(c);
        rectangle.setLocation(p);

        panel.add(rectangle);

        panel.revalidate();
        panel.repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_KP_UP:
            case KeyEvent.VK_UP:
                System.out.println("up");
                model.moveSnake(Direction.UP);
                break;
            case KeyEvent.VK_KP_RIGHT:
            case KeyEvent.VK_RIGHT:
                System.out.println("right");
                model.moveSnake(Direction.RIGHT);
                break;
            case KeyEvent.VK_KP_DOWN:
            case KeyEvent.VK_DOWN:
                System.out.println("down");
                model.moveSnake(Direction.DOWN);
                break;
            case KeyEvent.VK_KP_LEFT:
            case KeyEvent.VK_LEFT:
                System.out.println("left");
                model.moveSnake(Direction.LEFT);
                break;
            default:
                model.moveSnake();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
