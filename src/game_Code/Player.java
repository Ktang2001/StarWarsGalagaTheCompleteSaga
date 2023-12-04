package game_Code;

import java.util.ArrayList;

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
 this.setPlayerShip(playerShip);
 this.setDimenstions(width, height);
 }


public int getLives() {
 return this.lives;
}

public void setLives(int lives) {
 this.lives = lives;
}

public int getSpeed() {
 return this.speed;
}

public void setSpeed(int speed) {
 this.speed = speed;
}

public int getX() {
 return this.xpos;
}

public void setX(int x) {
 this.xpos = x;
}

public int getY() {
 return this.ypos;
}

public void setY(int y) {
 this.ypos = y;
}

public void setLocaiton(int x, int y) {
	this.ypos = y;
	this.xpos = x;
}
public void setWidth(int width) {
	this.width = width;
}
public void setHeight(int height) {
	this.height = height;
}
public int getWidth(int width) {
	 return this.width;
}
public int getHeight(int height) {
	return this.height;
}
public void setDimenstions(int width, int height) {
	this.width = width;
	this.height = height;
}

public void setPlayerShip(Ship playership) {
	this.playerShip = playership;
}
public Ship getPlayerShip() {
	return this.playerShip;
}


}

