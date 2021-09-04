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
        panel = new JPanel();
        panel.setBackground(new Color(216, 230, 209));
        panel.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.getContentPane().add(panel);
        this.addKeyListener(this);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void clear() {
        panel.removeAll();
        //panel.revalidate();
        //panel.repaint();
    }

    public void commitChange() {
        //panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void draw(Color c, Point p) {
        JPanel rectangle = new JPanel();
        rectangle.setSize(Constants.STEP_SIZE, Constants.STEP_SIZE);
        rectangle.setMinimumSize(new Dimension(Constants.STEP_SIZE, Constants.STEP_SIZE));
        rectangle.setMaximumSize(new Dimension(Constants.STEP_SIZE, Constants.STEP_SIZE));
        rectangle.setPreferredSize(new Dimension(Constants.STEP_SIZE, Constants.STEP_SIZE));

        panel.setLayout(null);

        rectangle.setBackground(c);
        rectangle.setLocation(p);

        panel.add(rectangle);
        //panel.revalidate();
        //panel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_KP_UP:
            case KeyEvent.VK_UP:
                model.actSnake(Direction.UP);
                break;
            case KeyEvent.VK_KP_RIGHT:
            case KeyEvent.VK_RIGHT:
                model.actSnake(Direction.RIGHT);
                break;
            case KeyEvent.VK_KP_DOWN:
            case KeyEvent.VK_DOWN:
                model.actSnake(Direction.DOWN);
                break;
            case KeyEvent.VK_KP_LEFT:
            case KeyEvent.VK_LEFT:
                model.actSnake(Direction.LEFT);
                break;
            default:
                model.actSnake();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
