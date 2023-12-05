package game_Code;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
	
	private int x, y;
	private int projectileWidth, projectileHeight;
	private int projectileSpeed;
	private Color color;
	
	public Projectile(int x, int y, int projectileWidth, int projectileHeight, int projectileSpeed, Color color) {
		this.x = x;
		this.y = y;
        this.projectileWidth = projectileWidth;
        this.projectileHeight = projectileHeight;
        this.projectileSpeed = 5;
        this.color = color;
	}
	
	public void move() {
		y -= projectileSpeed;
	}
	
	public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, projectileWidth, projectileHeight);
    }
	
	public int getX() {
        return this.x;
    }
	
	public int getY() {
        return this.y;
    }
	
	public int getWidth() {
        return this.projectileWidth;
    }
	
	public int getHeight() {
        return this.projectileHeight;
    }
	
	public int getProjectileSpeed() {
		return this.projectileSpeed;
	}
	
	public void setProjectileSpeed(int speed) {
		this.projectileSpeed = speed;
	}

	public boolean intersects(Projectile obstacleProjectile) {
		// Need to add code for collisions!!
		return false;
	}
}
