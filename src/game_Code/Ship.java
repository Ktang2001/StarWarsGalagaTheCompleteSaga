package game_Code;

import java.awt.Image;

import javax.swing.*;

public class Ship  {
	
	private int shipNum;
	private int numWep;
	private String playerShip = null; 
	private Image shipImage = null;
	private int height = 0;
	private int width = 0;
	
	public Ship(int shipNum, int numWep,String string,int height,int width) {
		this.setShipNum(shipNum);
		this.setNumWep(numWep);
	    this.shipImage = setImage(shipNum);
	    this.setDimenstions(width, height);
	}
	public void setShipNum( int shipNum) {
		this.shipNum = shipNum;
	}
	public void setNumWep(int numberOfWepons) {
		this.numWep = numberOfWepons;
	}
	public int getShipNum() {   
		return this.shipNum;
	}
	public int getNumWep() {
		return this.numWep;
	}
	public void setPlayerShip(String playership) {
		this.playerShip = playership;
	}
	public String getPlayership() {
		return this.playerShip;
	}
	public Image setImage(int shipNum) {
		switch(shipNum) {
		 case 1:
			 this.shipImage = new ImageIcon("N1_Starfigher.png").getImage().getScaledInstance(10,10, shipNum);
             break;
		 case 2:
			 this.shipImage = new ImageIcon("JediStarfighter.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 3:
			 this.shipImage = new ImageIcon("ARC-170.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 4:
			 this.shipImage = new ImageIcon("X-Wing.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 5:
			 this.shipImage = new ImageIcon("Y-Wing.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 6:
			 this.shipImage = new ImageIcon("falcon.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 7:
			 this.shipImage = new ImageIcon("VultureDroid.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 8:
			 this.shipImage = new ImageIcon("Tri-Fighter.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 9:
			 this.shipImage = new ImageIcon("battle-station.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 10:
			 this.shipImage = new ImageIcon("Grievous-Ship.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 11:
			 this.shipImage = new ImageIcon("malevolence-starship.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 12:
			 this.shipImage = new ImageIcon("Tie-Fighter.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 13:
			 this.shipImage = new ImageIcon("star-destroyer.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 14:
			 this.shipImage = new ImageIcon("executor-star-destroyer.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		 case 15:
			 this.shipImage = ( new ImageIcon("death-star.png").getImage().getScaledInstance(10,10, shipNum));
			 break;
		 case 16:
			 this.shipImage = new ImageIcon("death-star-2.png").getImage().getScaledInstance(10,10, shipNum);
			 break;
		}
		return this.shipImage;
	}
	public Image getImage() {
		return this.shipImage;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		 return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public void setDimenstions(int width, int height) {
		this.width = width;
		this.height = height;
	}
}
