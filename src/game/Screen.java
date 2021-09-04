package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JFrame implements KeyListener {

    private final JPanel contentPane;
    private final JLayeredPane layeredPane;
    private final JPanel panel;
    private final JPanel scorePanel;

    private final Model model;
    private volatile boolean aKeyIsPressed;

    public Screen(Model model) {
        super("Snake");
        this.aKeyIsPressed = false;
        this.model = model;
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
        contentPane = new JPanel();

        getContentPane().add(contentPane);

        layeredPane = new JLayeredPane();
        layeredPane.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        add(layeredPane);

        panel = new JPanel();
        panel.setBackground(new Color(216, 230, 209));
        panel.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

        int w = Constants.SCREEN_WIDTH/10;
        int h = Constants.SCREEN_HEIGHT/15;
        scorePanel.setSize(w, h);
        scorePanel.setLocation(new Point(Constants.SCREEN_WIDTH - w, 0));
        scorePanel.setBackground(new Color(233, 237, 232));
        scorePanel.add(new JLabel("Score: "));
        scorePanel.add(new JLabel("Highscore: "));

        layeredPane.add(panel, Integer.valueOf(0));
        layeredPane.add(scorePanel, Integer.valueOf(1));
        layeredPane.revalidate();
        layeredPane.repaint();

        this.addKeyListener(this);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void gameOverScreen() {
        aKeyIsPressed = false;
        SwingUtilities.invokeLater(new Thread(this::clear));

        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Game over, press any key to restart the game");
        label.setVisible(true);

        panel.add(label);
        commitChange();

        while (!aKeyIsPressed) {
            Thread.onSpinWait();
        }
        model.setGameOver(false);
    }

    public void clear() {
        panel.removeAll();
        panel.revalidate();
    }

    public void commitChange() {
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!model.isGameOver()) {
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
        SwingUtilities.invokeLater(new Thread(() -> aKeyIsPressed = true));
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
