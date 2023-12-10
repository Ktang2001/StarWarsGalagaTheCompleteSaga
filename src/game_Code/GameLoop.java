package game_Code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;


public class GameLoop extends JFrame {
	private static final long serialVersionUID = 1L;

    private Player player;
    private List<Opponent> opponents;
    private List<Projectile> projectiles;
    private boolean[] keyState = new boolean[256];
    private int score = 0;
    private int lives = 3;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    public GameLoop() {
        setTitle("Star Wars Attack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);

        player = new Player(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height - 180);
        opponents = new ArrayList<>();
        projectiles = new ArrayList<>();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
                
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_SPACE) {
                	player.shoot();
                }
                
                if (key >= 0 && key < 256) {
                	keyState[key] = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e);
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    private void move(KeyEvent e) {
    	int key = e.getKeyCode();
    	
        if (key == KeyEvent.VK_SPACE) {
        	player.shoot();
        }
    }

    private void generateOpponents() {
    	if (new Random().nextInt(100) < 2) {
            Opponent opponent = new Opponent(new Random().nextInt(width), 0);
            opponents.add(opponent);
        }
    }

    void updateGame() {
        player.move(getKeyState());
        generateOpponents();

        for (Projectile projectile : player.getProjectiles()) {
            projectile.move();
        }

        Iterator<Opponent> opponentIterator = opponents.iterator();
        while (opponentIterator.hasNext()) {
            Opponent opponent = opponentIterator.next();

            for (Projectile opponentProjectile : opponent.getProjectile()) {
                opponentProjectile.move();

                if (projectileIntersectsPlayer(opponentProjectile, player)) {
                    opponentIterator.remove();
                    lives--;  
                }
            }

            opponent.move();

            Iterator<Projectile> projectileIterator = player.getProjectiles().iterator();
            while (projectileIterator.hasNext()) {
                Projectile projectile = projectileIterator.next();

                if (projectileIntersectsOpponent(projectile, opponent)) {
                    opponentIterator.remove();
                    projectileIterator.remove();
                    score += 50;
                }
            }
        }

        checkCollisions();
        checkPlayerOutOfLives();
    }

    private void checkCollisions() {
        Iterator<Opponent> opponentIterator = opponents.iterator();
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (opponentIterator.hasNext()) {
            Opponent opponent = opponentIterator.next();
            Projectile bolt = null;  

            if (projectileIterator.hasNext()) {
                bolt = projectileIterator.next();
            }

            if (playerIntersectsOpponent(player, opponent)) {
                opponentIterator.remove();
                lives--;
            } else {
                
                if (bolt != null && projectileIntersectsPlayer(bolt, player)) {
                    projectileIterator.remove();
                    lives--;
                }
            }

            List<Projectile> projectilesToRemove = new ArrayList<>();
            for (Projectile projectile : projectiles) {
                if (projectileIntersectsOpponent(projectile, opponent)) {
                    projectilesToRemove.add(projectile);
                    score += 50;
                }
            }

           
            projectiles.removeAll(projectilesToRemove);
        }
    }

    private boolean playerIntersectsOpponent(Player player, Opponent opponent) {
        return (player.getX() < opponent.getX() + opponent.getWidth() &&
                player.getX() + player.getWidth() > opponent.getX() &&
                player.getY() < opponent.getY() + opponent.getHeight() &&
                player.getY() + player.getHeight() > opponent.getY());
    }
    
    private boolean projectileIntersectsPlayer(Projectile opponent, Player player) {
        return (opponent.getX() < player.getX() + player.getWidth() &&
                opponent.getX() + opponent.getWidth() > player.getX() &&
                opponent.getY() < player.getY() + player.getHeight() &&
                opponent.getY() + opponent.getHeight() > player.getY());
    }

    private boolean projectileIntersectsOpponent(Projectile projectile, Opponent opponent) {
        return (projectile.getX() < opponent.getX() + opponent.getWidth() &&
                projectile.getX() + projectile.getWidth() > opponent.getX() &&
                projectile.getY() < opponent.getY() + opponent.getHeight() &&
                projectile.getY() + projectile.getHeight() > opponent.getY());
    }

    private boolean[] getKeyState() {
        boolean[] keys = new boolean[256];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = keyState[i];
        }
        return keys;
    }
    
    public void keyPressed(KeyEvent e) {
    	move(e);
        int key = e.getKeyCode();
        if (key >= 0 && key < 256) {
            keyState[key] = true;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key >= 0 && key < 256) {
            keyState[key] = false;
        }
    }
    
    private void checkPlayerOutOfLives() {
        if (lives <= 0) {
        	Menu postGameMenu = new Menu();
            postGameMenu.showPostGameMenu(score);
            System.exit(0);
        }
    }

    private void drawGame(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,Toolkit.getDefaultToolkit().getScreenSize().width , Toolkit.getDefaultToolkit().getScreenSize().height);

        player.draw(g);

        for (Opponent opponent : opponents) {
            opponent.draw(g);
        }

        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 24));
        g.drawString("Score: " + score, 10, 100);
        g.drawString("Lives: " + lives, getWidth() - 125, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGame(g);
        for (Projectile projectile : player.getProjectiles()) {
            projectile.draw(g);
        }
    }
}


