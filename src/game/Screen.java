package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JFrame implements KeyListener {

    private final JPanel mainPanel;

    private final JLabel scoreLabel;
    private final JLabel highscoreLabel;

    private int highscore;

    private final Model model;
    private volatile boolean aKeyIsPressed;

    public Screen(Model model) {
        super("Snake");
        this.aKeyIsPressed = false;
        this.model = model;
        setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addKeyListener(this);
        setVisible(true);
        setResizable(false);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        getContentPane().add(layeredPane);

        this.mainPanel = new JPanel();
        mainPanel.setBackground(new Color(216, 230, 209));
        mainPanel.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setOpaque(false);
        scorePanel.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 15);

        scoreLabel = new JLabel("Score: 0");
        highscoreLabel = new JLabel("Highscore: " + (highscore = 0));

        scorePanel.add(scoreLabel);
        scorePanel.add(highscoreLabel);

        layeredPane.add(mainPanel, Integer.valueOf(0));
        layeredPane.add(scorePanel, Integer.valueOf(1));
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
        if (score > highscore) {
            highscore = score;
            highscoreLabel.setText("Highscore: " + highscore);
        }
    }

    public void gameOverScreen() {
        aKeyIsPressed = false;
        clear();

        mainPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Game over, press any key to restart the game");
        label.setVisible(true);

        mainPanel.add(label);
        mainPanel.revalidate();
        commitChange();

        while (!aKeyIsPressed) {
            Thread.onSpinWait();
        }
        model.setGameOver(false);
    }

    public void clear() {
        mainPanel.removeAll();
    }

    public void commitChange() {
        mainPanel.repaint();
    }

    public void draw(Color c, Point p) {
        JPanel rectangle = new JPanel();
        rectangle.setSize(Constants.STEP_SIZE, Constants.STEP_SIZE);
        mainPanel.setLayout(null);

        rectangle.setBackground(c);
        rectangle.setLocation(p);

        mainPanel.add(rectangle);
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
