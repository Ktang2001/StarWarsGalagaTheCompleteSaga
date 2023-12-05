package game_Code;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop extends JFrame {

    private static final long serialVersionUID = -602017958886020199L;
    private static final int PLAYER_SPEED = 5;
    private static final int OPPONENT_SPEED = 5;
    private static final int PLAYER_WIDTH = 10;
    private static final int PLAYER_HEIGHT = 10;
    private static final int OPPONENT_WIDTH = 10;
    private static final int OPPONENT_HEIGHT = 10;

    private List<String> shipImages;
    private Player player;
    private List<Object[]> opponents;

    public GameLoop() {
        initializeGame();
        setupUI();
    }

    private void initializeGame() {
        addKeyListener(new DirectionListener());
        shipImages = new ArrayList<>();

        shipImages.add("N1_Starfigher.png");   
        shipImages.add("JediStarfighter.png");
        shipImages.add("ARC-170.png");
        shipImages.add("X-Wing.png");
        shipImages.add("Y-Wing.png");
        shipImages.add("falcon.png");
        shipImages.add("VultureDroid.png");
        shipImages.add("Tri-Fighter.png");
        shipImages.add("battle-station.png");
        shipImages.add("Grievous-Ship.png");
        shipImages.add("malevolence-starship.png");
        shipImages.add("Tie-Fighter.png");
        shipImages.add("star-destroyer.png");
        shipImages.add("executor-star-destroyer.png");
        shipImages.add("death-star.png");
        shipImages.add("death-star-2.png");

        opponents = new ArrayList<>();

        Ship playerShip = new Ship(1, 2, shipImages.get(1), PLAYER_WIDTH, PLAYER_HEIGHT);
        playerShip.setWidth(PLAYER_WIDTH);
        playerShip.setHeight(PLAYER_HEIGHT);
        playerShip.setImage(playerShip.getShipNum(), playerShip.getHeight(), playerShip.getWidth());
        player = new Player(100, 100, 400, 600, playerShip, PLAYER_SPEED, PLAYER_SPEED);

        Timer timer = new Timer(16, new ActionListener() { // 60 frames per second
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                repaint();
            }
        });
        timer.start();
    }

    private void setupUI() {
        JPanel space = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };
        setContentPane(space);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setFocusable(true);
        setVisible(true);
    }

    private class DirectionListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    player.setY(player.getY() - PLAYER_SPEED);
                    break;
                case KeyEvent.VK_DOWN:
                    player.setY(player.getY() + PLAYER_SPEED);
                    break;
                case KeyEvent.VK_LEFT:
                    player.setX(player.getX() - PLAYER_SPEED);
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setX(player.getX() + PLAYER_SPEED);
                    break;
            }

            repaint();
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private void updateGame() {
        updateOpponents();
    }

    private void updateOpponents() {
        Random rand = new Random();
        int num = 1;
        int randX, randShip;
        Ship opponentShip;

        opponents.clear();

        for (int i = 0; i < num; i++) {
            randX = rand.nextInt(0, getWidth() - OPPONENT_WIDTH);
            randShip = rand.nextInt(7, 11);
            opponentShip = new Ship(randShip, 2, shipImages.get(randShip), OPPONENT_WIDTH, OPPONENT_HEIGHT);
            opponentShip.setImage(randShip, OPPONENT_WIDTH, OPPONENT_HEIGHT);
            Object[] opponent = new Object[]{randX, 0, opponentShip, OPPONENT_SPEED, OPPONENT_SPEED};
            opponents.add(opponent);
        }

        for (Object[] opponent : opponents) {
            int opponentY = (int) opponent[1];
            opponentY += OPPONENT_SPEED;
            opponent[1] = opponentY;
        }
    }
// Push
    private void drawGame(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        Image scaledPlayerShip = player.getPlayerShip().getImage().getScaledInstance(PLAYER_WIDTH, PLAYER_HEIGHT, Image.SCALE_SMOOTH);
        g.drawImage(scaledPlayerShip, player.getX(), player.getY(), this);

        for (Object[] opponent : opponents) {
            int opponentX = (int) opponent[0];
            int opponentY = (int) opponent[1];
            Ship opponentShip = (Ship) opponent[2];

            Image scaledOpponentShip = opponentShip.getImage().getScaledInstance(OPPONENT_WIDTH, OPPONENT_HEIGHT, Image.SCALE_SMOOTH);
            g.drawImage(scaledOpponentShip, opponentX, opponentY, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameLoop();
        });
    }
}
