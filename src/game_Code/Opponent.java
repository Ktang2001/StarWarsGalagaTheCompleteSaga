package game_Code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.*;


public class Opponent {
	 private int x, y;
     private int width, height;
     private int speed;
     private Image image;
     private static final int OPPONENT_WIDTH = 50;
     private static final int OPPONENT_HEIGHT = 50;
     private static final int OPPONENT_SPEED = 5;
     private static final int PROJECTILE_WIDTH = 50;
     private static final int PROJECTILE_HEIGHT = 50;
     private static final Color RED = Color.RED;
     private List<Projectile> projectiles;

     public Opponent() {
         x = new Random().nextInt(width - OPPONENT_WIDTH);
         image = new ImageIcon("VultureDroid.png").getImage();
         image = image.getScaledInstance(OPPONENT_WIDTH, OPPONENT_HEIGHT, Image.SCALE_DEFAULT);
         y = 0;
         width = OPPONENT_WIDTH;
         height = OPPONENT_HEIGHT;
         speed = OPPONENT_SPEED;
         projectiles = new ArrayList<>();
     }

     public void move() {
         y += speed;
         if (y > height) {
             resetPosition();
         }
     }

     public void resetPosition() {
         x = new Random().nextInt(width - OPPONENT_WIDTH);
         y = 0;
     }

     public void shoot() {
    	 int projectileX = x + (OPPONENT_WIDTH / 2) - (PROJECTILE_WIDTH / 2);
         int projectileY = y;
         int projectileWidth = PROJECTILE_WIDTH; 
         int projectileHeight = PROJECTILE_HEIGHT; 
         int projectileSpeed = 5; 
    	 Projectile newProjectile = new Projectile(projectileX, projectileY, projectileWidth, projectileHeight, projectileSpeed, RED);
         newProjectile.setProjectileSpeed(projectileSpeed);
         projectiles.add(newProjectile);
     }

     public void updateProjectiles() {
         Iterator<Projectile> iterator = projectiles.iterator();
         while (iterator.hasNext()) {
             Projectile projectile = iterator.next();
             projectile.move();
             if (projectile.getY() > PROJECTILE_HEIGHT) {
                 iterator.remove();
             }
         }
     }

     public void draw(Graphics g, Image opponentImage) {
         g.drawImage(opponentImage , x, y, null);
         for (Projectile projectile : projectiles) {
             projectile.draw(g);
         }
     }

     public Image getImage() {
         return this.image;
     }
     
     public void setImage(Image playerShip) {
     	this.image = playerShip;
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
 
}
