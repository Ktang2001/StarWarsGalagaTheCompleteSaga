package game_Code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel implements ActionListener, KeyListener{
	private Image image;
	private boolean engineFlameVisible;
    private int x, y, lives;
    private List<Projectile> projectiles;
    
    private static final int PLAYER_WIDTH = 50;
    private static final int PLAYER_HEIGHT = 50;
    private static final int PLAYER_SPEED = 5;
    private static final int PLAYER_LIVES = 3;
    private static final int PROJECTILE_WIDTH = 5;
    private static final int WIDTH = 800; 
    private static final int HEIGHT = 600; 
    private static final Color BLUE = Color.BLUE;
    
    public Player() {
    	image = new ImageIcon("N1_Starfighter.png").getImage();
        image = image.getScaledInstance(PLAYER_WIDTH, PLAYER_HEIGHT, Image.SCALE_DEFAULT);
        resetPosition();
        lives = PLAYER_LIVES;
        projectiles = new ArrayList<>();
        engineFlameVisible = false;

        Timer timer = new Timer(16, null);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
    
    private void setX(int x) {
    	this.x = x;
    }
    
    private void setY(int y) {
    	this.y = y;
    }
    
    private void resetPosition() {
        x = (WIDTH - PLAYER_WIDTH) / 2;
        y = HEIGHT - PLAYER_HEIGHT;
    }
    
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
    
    private void move() {
        // Handle player movement logic here
        

    	List<Projectile> projectilesToRemove = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.move();

            if (projectile.getY() < 0 || projectile.getY() > HEIGHT) {
                projectilesToRemove.add(projectile);
            }
        }
        
        projectiles.removeAll(projectilesToRemove);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
        drawProjectiles(g);
    }
    
    private void drawPlayer(Graphics g) {
    	g.drawImage(image, x, y, this);
        if (engineFlameVisible) {
            g.setColor(Color.RED);
            g.fillRect(x + PLAYER_WIDTH / 2 - 5, y + PLAYER_HEIGHT, 10, 20);
        }
    }
    
    private void drawProjectiles(Graphics g) {
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }
    
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            x -= PLAYER_SPEED;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            x += PLAYER_SPEED;
        } else if (keyCode == KeyEvent.VK_UP) {
            y -= PLAYER_SPEED;
            engineFlameVisible = true;
            if (y < 0) {
                y = 0;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            y += PLAYER_SPEED;
            if (y > HEIGHT - PLAYER_HEIGHT) {
                y = HEIGHT - PLAYER_HEIGHT;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            shoot();
        }

        if (x < 0) {
            x = WIDTH - PLAYER_WIDTH;
        } else if (x > WIDTH - PLAYER_WIDTH) {
            x = 0;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            engineFlameVisible = false;
        }
    }
    
    public void keyTyped(KeyEvent e) {
        
    }
    
    private void shoot() {
    	int projectileX = x + (PLAYER_WIDTH / 2) - (PROJECTILE_WIDTH / 2);
        int projectileY = y;
        int projectileWidth = PROJECTILE_WIDTH; 
        int projectileHeight = PROJECTILE_WIDTH; 
        int projectileSpeed = 5; 
        
        Projectile newProjectile = new Projectile(projectileX, projectileY, projectileWidth, projectileHeight, projectileSpeed, BLUE);
        newProjectile.setProjectileSpeed(projectileSpeed);
        projectiles.add(newProjectile);
    }
    
    private void handleLives(int damageAmount) {
    	lives -= damageAmount;

        if (this.PLAYER_LIVES <= 0) {
            gameOver();
        }
    }

    // Additional methods for handling collisions, updating lives
    

}

