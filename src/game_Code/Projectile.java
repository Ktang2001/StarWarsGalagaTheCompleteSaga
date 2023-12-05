package game_Code;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile {
	
	private int x, y;
	private int width, height;
	private int projectileSpeed;
	private Color color;
	
	public Projectile(int x, int y, int width, int height, int projectileSpeed, Color color) {
		this.x = x;
		this.y = y;
        this.width = width;
        this.height = height;
        this.projectileSpeed = 5;
        this.color = color;
	}
	
	public void move() {
		y -= projectileSpeed;
	}
	
	public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
	
	public int getX() {
        return this.x;
    }
	
	public int getY() {
        return this.y;
    }
	
	public int getWidth() {
        return this.width;
    }
	
	public int getHeight() {
        return this.height;
    }
	
	public int getProjectileSpeed() {
		return this.projectileSpeed;
	}
}
