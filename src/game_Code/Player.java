package game_Code;


public class Player {


private int lives; 
private int speed; 
private int xpos; 
private int ypos; 
private Ship playerShip ;
private int width;
private int height;


public Player(int lives,int speed,int x,int y ,Ship playerShip,int width,int height) {
 this.setLives(lives);
 this.setSpeed(speed);
 this.setLocaiton(x, y);
 this.playerShip = playerShip;
 this.width = width;
 this.height = height;
 }


public int getLives() {
 return lives;
}

public void setLives(int lives) {
 this.lives = lives;
}

public int getSpeed() {
 return speed;
}

public void setSpeed(int speed) {
 this.speed = speed;
}

public int getX() {
 return xpos;
}

public void setX(int x) {
 this.xpos = x;
}

public int getY() {
 return ypos;
}

public void setY(int y) {
 this.ypos = y;
}

public void setLocaiton(int x, int y) {
	this.ypos = y;
	this.xpos = x;
}
public void setDimenstions(int width, int height) {
	this.width = width;
	this.height = height;
}


}

