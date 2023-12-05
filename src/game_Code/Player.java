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

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private Image image;
    private int x, y, lives;
    private List<Projectile> projectiles;
    private boolean engineFlameVisible;

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
    }

    public void resetPosition() {
        x = (WIDTH - PLAYER_WIDTH) / 2;
        y = HEIGHT - PLAYER_HEIGHT;
    }

    public void move(boolean[] keys) {
        if (keys[KeyEvent.VK_LEFT]) {
            x -= PLAYER_SPEED;
        }
        if (keys[KeyEvent.VK_RIGHT]) {
            x += PLAYER_SPEED;
        }
        if (keys[KeyEvent.VK_UP]) {
            y -= PLAYER_SPEED;
            engineFlameVisible = true;
            if (y < 0) {
                y = 0;
            }
        } else {
            engineFlameVisible = false;
        }
        if (keys[KeyEvent.VK_DOWN]) {
            y += PLAYER_SPEED;
            if (y > HEIGHT - PLAYER_HEIGHT) {
                y = HEIGHT - PLAYER_HEIGHT;
            }
        }

        if (x < 0) {
            x = WIDTH - PLAYER_WIDTH;
        } else if (x > WIDTH - PLAYER_WIDTH) {
            x = 0;
        }
    }

    public void shoot() {
        int projectileX = x + (PLAYER_WIDTH / 2) - (PROJECTILE_WIDTH / 2);
        int projectileY = y;
        int projectileWidth = PLAYER_WIDTH;
        int projectileHeight = PLAYER_HEIGHT;
        int projectileSpeed = 5; 
   	 	Projectile newProjectile = new Projectile(projectileX, projectileY, projectileWidth, projectileHeight, projectileSpeed, BLUE);
        newProjectile.setProjectileSpeed(projectileSpeed);
        projectiles.add(newProjectile);
    }

    public Image getImage() {
        return this.image;
    }
    
    public void setImage(Image playerShip) {
    	this.image = playerShip;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }

    public List<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public boolean isEngineFlameVisible() {
        return engineFlameVisible;
    }

    public int getLives() {
        return this.lives;
    }
    
    public void setLives(int lives) {
    	this.lives = lives;
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

    // Additional methods for handling collisions, updating lives

}

