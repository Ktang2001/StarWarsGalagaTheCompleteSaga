package game_Code;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {
	private int x, y;
    private int width, height;
    private Image image;
    private int speed = 5;
    private boolean moveLeft, moveRight;
    private List<Projectile> projectiles;
    private boolean isSpaceKeyPressed;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 150;
        this.height = 141;
        this.projectiles = new ArrayList<>();
        this.image = new ImageIcon("JediStarfighter.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    public void move(boolean[] keys) {
    	if (moveLeft && x > 0) {
            x -= speed;
        }
        if (moveRight && x < Toolkit.getDefaultToolkit().getScreenSize().width - width) {
            x += speed;
        }
    }
    
    public void shoot() {
        projectiles.add(new Projectile(x + width / 2, y, true));
    }
    
    public void draw(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public List<Projectile> getProjectiles() {
        return projectiles;
    }
	
	public boolean isSpaceKeyPressed() {
        return isSpaceKeyPressed;
    }
	
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            moveLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            moveRight = true;
        } else if (key == KeyEvent.VK_SPACE) {
            isSpaceKeyPressed = true;
        }
	}
	
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            moveLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            moveRight = false;
        } else if (key == KeyEvent.VK_SPACE) {
            isSpaceKeyPressed = false;
        }
    }
}

