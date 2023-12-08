package game_Code;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Projectile {
    private int x, y;
    private int width, height;
    private Image image;
    private int speed = 7;
    private boolean isPlayerProjectile;

    public Projectile(int x, int y, boolean isPlayerProjectile) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 20;
        this.isPlayerProjectile = isPlayerProjectile;

        if (isPlayerProjectile) {
            this.image = new ImageIcon("blaster-bolt.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } else {
            this.image = new ImageIcon("blaster-bolt-2.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        }
    }

    public void move() {
        if (isPlayerProjectile) {
            y -= speed;
        } else {
            y += speed;
        }
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

    public boolean isPlayerProjectile() {
        return isPlayerProjectile;
    }

}