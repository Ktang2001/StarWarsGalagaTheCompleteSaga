package game_Code;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class GameLoop extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int MENU = 0;
    private static final int GAME = 1;

    private int width, height;
    private Image playerImage, obstacleImage;
    private int playerWidth = 50, playerHeight = 75;
    private int obstacleWidth = 50, obstacleHeight = 50;
    private int playerSpeed = 5, obstacleSpeed = 3;
    private int playerLives = 3;
    private int score = 0;
    private List<Opponent> obstacles = new ArrayList<Opponent>();
    private Player player;
    private float enemyFireRate = 1.0f;
    private float enemyFireCooldown = 0;

    public GameLoop() {
        setTitle("Star Wars Attack: The Complete Saga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        width = 1000;
        height = 700;
        setSize(width, height);

        playerImage = new ImageIcon("N1_Starfighter.png").getImage().getScaledInstance(playerWidth, playerHeight, Image.SCALE_SMOOTH);
        obstacleImage = new ImageIcon("VultureDroid.png").getImage().getScaledInstance(obstacleWidth, obstacleHeight, Image.SCALE_SMOOTH);
        player = new Player();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void handleKeyPress(KeyEvent e) {
        if (player.getGameState() == MENU) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                startGame();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        } else if (player.getGameState() == GAME) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.shoot();
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                player.setGameState(MENU);
            } else {
                player.handleKeyPress(e);
            }
        }
    }

    private void startGame() {
        player.setGameState(GAME);
        player.reset();
        obstacles.clear();
        score = 0;
        player.setLives(playerLives);
        player.resetPosition();
    }

    private void generateObstacles() {
        if (new Random().nextInt(100) < 10) {
            Opponent obstacle = new Opponent();
            obstacles.add(obstacle);
        }
    }

    private void updateGame() {
        if (player.getGameState() == GAME) {
        	boolean[] keys = new boolean[256];
        	
            player.move(keys);

            generateObstacles();

            Iterator<Opponent> iterator = obstacles.iterator();
            while (iterator.hasNext()) {
                Opponent obstacle = iterator.next();
                obstacle.move();
                if (obstacle.getY() > height) {
                    iterator.remove();
                    score += 10;
                }

                if (new Random().nextFloat() < enemyFireRate && enemyFireCooldown <= 0) {
                    obstacle.shoot();
                    enemyFireCooldown = 1.0f / enemyFireRate;
                }
                
                List<Projectile> opponentProjectilesToRemove = new ArrayList<>();
                for (Projectile projectile : obstacle.getProjectiles()) {
                    projectile.move();
                    if (projectile.getY() > height) {
                        opponentProjectilesToRemove.add(projectile);
                    }
                }
                obstacle.getProjectiles().removeAll(opponentProjectilesToRemove);
            }
            

            if (enemyFireCooldown > 0) {
                enemyFireCooldown -= 1.0 / 60;
            }

            player.updateProjectiles();

            checkCollisions();
            
            checkPlayerOutOfLives();
        }
    }

    private void checkCollisions() {
        Iterator<Projectile> playerProjectilesIterator = player.getProjectiles().iterator();
        while (playerProjectilesIterator.hasNext()) {
            Projectile playerProjectile = playerProjectilesIterator.next();

            Iterator<Opponent> obstaclesIterator = obstacles.iterator();
            while (obstaclesIterator.hasNext()) {
                Opponent obstacle = obstaclesIterator.next();

                Iterator<Projectile> obstacleProjectilesIterator = obstacle.getProjectiles().iterator();
                while (obstacleProjectilesIterator.hasNext()) {
                    Projectile obstacleProjectile = obstacleProjectilesIterator.next();

                    if (playerProjectile.intersects(obstacleProjectile)) {
                        playerProjectilesIterator.remove();
                        obstacleProjectilesIterator.remove();
                        obstaclesIterator.remove();
                        score += 10;
                    }
                }
            }
        }

        Iterator<Opponent> obstaclesIterator = obstacles.iterator();
        while (obstaclesIterator.hasNext()) {
            Opponent obstacle = obstaclesIterator.next();
            if (player.intersects(obstacle)) {
                player.setLives(player.getLives() - 1);
                obstaclesIterator.remove();
            }
        }
    }

    private void checkPlayerOutOfLives() {
        if (player.getLives() <= 0) {
            player.setGameState(MENU);
        }
    }

    private void drawGame(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,Toolkit.getDefaultToolkit().getScreenSize().width , Toolkit.getDefaultToolkit().getScreenSize().height);

        if (player.getGameState() == MENU) {
            g.setColor(Color.WHITE);
            g.drawString("Press SPACE to Start or ESC to Exit",Toolkit.getDefaultToolkit().getScreenSize().width/2  , Toolkit.getDefaultToolkit().getScreenSize().height/2);
        } else if (player.getGameState() == GAME) {
            player.draw(g);

            for (Opponent obstacle : obstacles) {
                obstacle.draw(g, obstacleImage);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGame(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameLoop game = new GameLoop();
            game.setExtendedState(JFrame.MAXIMIZED_BOTH);
            game.setVisible(true);

            Timer timer = new Timer(16, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    game.updateGame();
                    game.repaint();
                }
            });

            timer.start();
        });
    }
}


