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
     private Image image;
     private List<Projectile> projectiles;
     private float fireRate = 0.02f;
     private float fireCooldown = 0;
     

     public Opponent(int x, int y){
    	 this.x = x;
    	 this.y = y;
    	 this.width = 75;
    	 this.height = 125;
    	 this.image = new ImageIcon("VultureDroid.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
         this.projectiles = new ArrayList<>();
     }

     public void move() {
         y += 3;
         if (y > 600) {
             resetPosition();
         }
         tryShoot();
         updateProjectiles();
     }
     
     private void tryShoot() {
    	 if(new Random().nextFloat() < fireRate && fireCooldown <= 0) {
    		 shoot();
    		 fireCooldown = 1.0f / fireRate;
    	 }
     }
     
     private void shoot() {
    	 projectiles.add(new Projectile(x + width / 2, y + height, false));
     }
     
     private void updateProjectiles() {
    	 Iterator<Projectile> iterator = projectiles.iterator();
    	 while (iterator.hasNext()) {
    		 Projectile projectile = iterator.next();
    		 projectile.move();
    		 if(projectile.getY() > 600 || projectile.getY() < 0) {
    			 iterator.remove();
    		 }
    	 }
    	 if (fireCooldown > 0) {
    		 fireCooldown -= 1.0 / 60;
    	 }
     }

     public void resetPosition() {
         x = new Random().nextInt(750);
         y = 0;
     }
     
     public void draw (Graphics g) {
    	 g.drawImage(image,  x,  y,  null);
    	 
    	 for(Projectile projectile : projectiles) {
    		 projectile.draw(g);
    	 }
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
     
     public List<Projectile> getProjectile(){
    	 return projectiles;
     }
}
